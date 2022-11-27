import { Module } from '@nestjs/common';
import { FanService } from './fan.service';
import { FanController } from './fan.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { FanEntity } from './entity/fan.entity';
import { LoveListEntity } from './entity/love-list.entity';

@Module({
  imports: [TypeOrmModule.forFeature([FanEntity, LoveListEntity])],
  providers: [FanService],
  controllers: [FanController],
})
export class FanModule {}
