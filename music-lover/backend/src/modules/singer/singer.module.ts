import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AwardEntity } from '../award/entity/award.entity';
import { SingerAwardEntity } from './entity/singer-award.entity';
import { SingerEntity } from './entity/singer.entity';
import { TrackListEntity } from './entity/track-list.entity';
import { AlbumService } from './album.service';
import { SingerService } from './singer.service';
import { AlbumRepository } from './repository/album.repository';
import { SingerRepository } from './repository/singer.repository';
import { SingerAwardRepository } from './repository/singer-award.repository';
import { TrackListRepository } from './repository/track-list.repository';
import { SingerController } from './singer.controller';
import { AlbumController } from './album.controller';
import { AlbumEntity } from './entity/album.entity';

@Module({
  imports: [
    TypeOrmModule.forFeature([
      AlbumEntity,
      AwardEntity,
      SingerEntity,
      SingerAwardEntity,
      TrackListEntity,
    ]),
  ],
  providers: [
    AlbumService,
    SingerService,
    AlbumRepository,
    SingerRepository,
    SingerAwardRepository,
    TrackListRepository,
  ],
  exports: [AlbumService, SingerService],
  controllers: [SingerController, AlbumController],
})
export class SingerModule {}
