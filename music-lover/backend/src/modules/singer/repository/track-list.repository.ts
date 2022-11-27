import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { TrackListCreateDto } from '../dto/track-list-create.dto';
import { TrackListUpdateDto } from '../dto/track-list-update.dto';
import { AlbumEntity } from '../entity/album.entity';
import { TrackListEntity } from '../entity/track-list.entity';

export class TrackListRepository {
  constructor(
    @InjectRepository(TrackListEntity)
    private trackListRepository: Repository<TrackListEntity>,
  ) {}

  create(dto: TrackListCreateDto, albumId: string): TrackListEntity {
    const album = new AlbumEntity(albumId);
    const entity = this.trackListRepository.create(dto);
    entity.album = album;
    console.log(entity);
    return entity;
  }

  async save(entity: TrackListEntity) {
    await this.trackListRepository.save(entity);
  }

  async update(id: string, dto: TrackListUpdateDto) {
    return this.trackListRepository.update(id, {
      ...(dto.length && { length: dto.length }),
      ...(dto.name && { name: dto.name }),
      ...(dto.writer && { writer: dto.writer }),
    });
  }

  async deleteById(id: string) {
    return this.trackListRepository
      .createQueryBuilder()
      .delete()
      .from(TrackListEntity)
      .where('id = :id', { id: id })
      .execute();
  }
}
