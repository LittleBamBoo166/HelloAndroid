import { Module } from '@nestjs/common';
import { AwardService } from './award.service';
import { AwardController } from './award.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AwardEntity } from './entity/award.entity';
import { AwardRepository } from './repository/award.repository';

@Module({
  imports: [TypeOrmModule.forFeature([AwardEntity])],
  providers: [AwardService, AwardRepository],
  controllers: [AwardController],
  exports: [AwardService],
})
export class AwardModule {}
