import { Injectable } from '@nestjs/common';
import { SingerAwardCreateDto } from './dto/singer-award-create.dto';
import { SingerAwardUpdateDto } from './dto/singer-award-update.dto';
import { SingerCreateDto } from './dto/singer-create.dto';
import { SingerUpdateDto } from './dto/singer-update.dto';
import { SingerAwardEntity } from './entity/singer-award.entity';
import { SingerEntity } from './entity/singer.entity';
import { SingerAwardRepository } from './repository/singer-award.repository';
import { SingerRepository } from './repository/singer.repository';

@Injectable()
export class SingerService {
  constructor(
    private singerRepository: SingerRepository,
    private singerAwardRepository: SingerAwardRepository,
  ) {}

  async login(name: string, pass: string): Promise<SingerEntity> {
    return this.singerRepository.login(name, pass);
  }

  async getNumberOfAlbums(id: string) {
    const numberOfAlbums = await this.singerRepository.getNumberOfAlbums(id);
    return {
      numberOfAlbums: numberOfAlbums,
    };
  }

  async getOneById(id: string): Promise<SingerEntity> {
    return this.singerRepository.getOneById(id);
  }

  async create(dto: SingerCreateDto) {
    const entity = this.singerRepository.create(dto);
    await this.singerRepository.save(entity);
    return {
      id: entity.id,
    };
  }

  async update(id: string, dto: SingerUpdateDto) {
    await this.singerRepository.update(id, dto);
  }

  async getAwards(singerId: string): Promise<SingerAwardEntity[]> {
    return this.singerAwardRepository.getMany(singerId);
  }

  async addAward(dto: SingerAwardCreateDto, singerId: string) {
    const entity = this.singerAwardRepository.create(dto, singerId);
    await this.singerAwardRepository.save(entity);
    return {
      id: entity.id,
    };
  }

  async updateAward(id: string, dto: SingerAwardUpdateDto) {
    await this.singerAwardRepository.update(id, dto);
  }

  async removeAward(id: string) {
    await this.singerAwardRepository.deleteById(id);
  }
}
