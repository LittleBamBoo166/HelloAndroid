import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { SingerCreateDto } from '../dto/singer-create.dto';
import { SingerUpdateDto } from '../dto/singer-update.dto';
import { SingerEntity } from '../entity/singer.entity';

export class SingerRepository {
  constructor(
    @InjectRepository(SingerEntity)
    private singerRepository: Repository<SingerEntity>,
  ) {}

  async login(name: string, pass: string): Promise<SingerEntity> {
    const singer = this.singerRepository.findOneBy({
      name: name,
      password: pass,
    });
    if (singer) {
      return singer;
    }
    return null;
  }

  async getNumberOfAlbums(id: string): Promise<number> {
    return this.singerRepository
      .createQueryBuilder('singer_entity')
      .leftJoin('singer_entity.albums', 'album_entity')
      .where('singer_entity.id = :id', { id: id })
      .getCount();
  }

  async getOneById(id: string): Promise<SingerEntity> {
    return this.singerRepository.findOneBy({ id: id });
  }

  async getMany(): Promise<SingerEntity[]> {
    return this.singerRepository.find();
  }

  create(dto: SingerCreateDto): SingerEntity {
    return this.singerRepository.create(dto);
  }

  async save(entity: SingerEntity) {
    await this.singerRepository.save(entity);
  }

  async update(id: string, dto: SingerUpdateDto) {
    return this.singerRepository.update(id, {
      ...(dto.address && { address: dto.address }),
      ...(dto.name && { name: dto.name }),
      ...(dto.yearActivate && { yearActivate: dto.yearActivate }),
    });
  }

  async deleteById(id: string) {
    await this.singerRepository
      .createQueryBuilder()
      .delete()
      .from(SingerEntity)
      .where('id = :id', { id: id })
      .execute();
  }
}
