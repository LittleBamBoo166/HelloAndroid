import { InjectRepository } from '@nestjs/typeorm';
import { AwardEntity } from 'src/modules/award/entity/award.entity';
import { Repository } from 'typeorm';
import { SingerAwardCreateDto } from '../dto/singer-award-create.dto';
import { SingerAwardUpdateDto } from '../dto/singer-award-update.dto';
import { SingerAwardEntity } from '../entity/singer-award.entity';
import { SingerEntity } from '../entity/singer.entity';

export class SingerAwardRepository {
  constructor(
    @InjectRepository(SingerAwardEntity)
    private singerAwardRepository: Repository<SingerAwardEntity>,
  ) {}

  async getOneById(id: string): Promise<SingerAwardEntity> {
    return this.singerAwardRepository.findOneBy({ id: id });
  }

  async getMany(singerId: string): Promise<SingerAwardEntity[]> {
    return this.singerAwardRepository
      .createQueryBuilder('singer_award_entity')
      .leftJoin('singer_award_entity.singer', 'singer')
      .where('singer.id = :id', { id: singerId })
      .getMany();
  }

  create(dto: SingerAwardCreateDto, singerId: string): SingerAwardEntity {
    const singer = new SingerEntity(singerId);
    const award = new AwardEntity(dto.awardId);
    const entity = this.singerAwardRepository.create(dto);
    entity.singer = singer;
    entity.award = award;
    return entity;
  }

  async save(entity: SingerAwardEntity) {
    await this.singerAwardRepository.save(entity);
  }

  async update(id: string, dto: SingerAwardUpdateDto) {
    return this.singerAwardRepository.update(id, {
      ...(dto.category && { category: dto.category }),
      ...(dto.year && { year: dto.year }),
    });
  }

  async deleteById(id: string) {
    await this.singerAwardRepository
      .createQueryBuilder()
      .delete()
      .from(SingerAwardEntity)
      .where('id = :id', { id: id })
      .execute();
  }
}
