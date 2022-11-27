import { Injectable } from '@nestjs/common';
import { AwardCreateDto } from './dto/award-create.dto';
import { AwardUpdateDto } from './dto/award-update.dto';
import { AwardEntity } from './entity/award.entity';
import { AwardRepository } from './repository/award.repository';

@Injectable()
export class AwardService {
  constructor(private awardRepository: AwardRepository) {}

  async getOneById(id: string): Promise<AwardEntity> {
    return this.awardRepository.getOneById(id);
  }

  async getMany(): Promise<AwardEntity[]> {
    return this.awardRepository.getMany();
  }

  async create(data: AwardCreateDto) {
    const entity = this.awardRepository.create(data);
    await this.awardRepository.save(entity);
    return {
      id: entity.id,
    };
  }

  async update(id: string, data: AwardUpdateDto) {
    await this.awardRepository.update(id, data);
    return {
      id: id,
    };
  }

  async deleteById(id: string): Promise<void> {
    await this.awardRepository.deleteById(id);
  }
}
