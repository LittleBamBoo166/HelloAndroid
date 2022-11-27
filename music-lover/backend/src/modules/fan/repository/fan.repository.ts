import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { FanEntity } from '../entity/fan.entity';

@Injectable()
export class FanRepository {
  constructor(
    @InjectRepository(FanEntity) private fanRepository: Repository<FanEntity>,
  ) {}
}
