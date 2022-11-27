import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { AlbumCreateDto } from '../dto/album-create.dto';
import { AlbumUpdateDto } from '../dto/album-update.dto';
import { AlbumEntity } from '../entity/album.entity';
import { SingerEntity } from '../entity/singer.entity';

export class AlbumRepository {
  constructor(
    @InjectRepository(AlbumEntity)
    private albumRepository: Repository<AlbumEntity>,
  ) {}

  async getOneById(id: string): Promise<AlbumEntity> {
    return this.albumRepository
      .createQueryBuilder('album_entity')
      .leftJoinAndSelect('album_entity.singer', 'singer')
      .leftJoinAndSelect('album_entity.trackList', 'track_list')
      .where('album_entity.id = :id', { id: id })
      .getOne();
  }

  async getNumberOfSongs(id: string): Promise<number> {
    return this.albumRepository
      .createQueryBuilder('album_entity')
      .leftJoinAndSelect('album_entity.trackList', 'track_list')
      .where('album_entity.id = :id', { id: id })
      .getCount();
  }

  async getMany(singerId: string): Promise<AlbumEntity[]> {
    return this.albumRepository
      .createQueryBuilder('album_entity')
      .leftJoin('album_entity.singer', 'singer_entity')
      .where('singer_entity.id = :id', { id: singerId })
      .getMany();
  }

  create(dto: AlbumCreateDto, singerId: string): AlbumEntity {
    const singer = new SingerEntity(singerId);
    const entity = this.albumRepository.create(dto);
    entity.singer = singer;
    return entity;
  }

  async save(entity: AlbumEntity): Promise<void> {
    await this.albumRepository.save(entity);
  }

  async update(id: string, dto: AlbumUpdateDto) {
    return this.albumRepository.update(id, {
      ...(dto.imageUrl && { imageUrl: dto.imageUrl }),
      ...(dto.link && { link: dto.link }),
      ...(dto.name && { name: dto.name }),
      ...(dto.releaseDate && { releaseDate: dto.releaseDate }),
    });
  }

  async deleteById(id: string): Promise<void> {
    await this.albumRepository
      .createQueryBuilder()
      .delete()
      .from(AlbumEntity)
      .where('id = :id', { id: id })
      .execute();
  }
}
