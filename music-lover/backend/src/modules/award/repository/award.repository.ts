import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { AwardCreateDto } from '../dto/award-create.dto';
import { AwardUpdateDto } from '../dto/award-update.dto';
import { AwardEntity } from '../entity/award.entity';

export class AwardRepository {
  constructor(
    @InjectRepository(AwardEntity)
    private awardRepository: Repository<AwardEntity>,
  ) {}

  async getOneById(id: string): Promise<AwardEntity> {
    return this.awardRepository.findOneBy({ id: id });
  }

  async getMany(): Promise<AwardEntity[]> {
    return this.awardRepository.find();
  }

  async save(entity: AwardEntity): Promise<void> {
    await this.awardRepository.save(entity);
  }

  async update(id: string, dto: AwardUpdateDto) {
    return this.awardRepository.update(id, {
      ...(dto.country && { country: dto.country }),
      ...(dto.location && { location: dto.location }),
      ...(dto.name && { name: dto.name }),
      ...(dto.website && { website: dto.website }),
    });
  }

  create(dto: AwardCreateDto): AwardEntity {
    return this.awardRepository.create(dto);
  }

  async deleteById(id: string): Promise<void> {
    await this.awardRepository
      .createQueryBuilder()
      .delete()
      .from(AwardEntity)
      .where('id = :id', { id: id })
      .execute();
  }
}
