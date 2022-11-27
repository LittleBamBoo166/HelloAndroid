import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AuthModule } from './auth/auth.module';
import { FanModule } from './modules/fan/fan.module';
import { SingerModule } from './modules/singer/singer.module';
import { AwardModule } from './modules/award/award.module';
import { AwardEntity } from './modules/award/entity/award.entity';
import { FanEntity } from './modules/fan/entity/fan.entity';
import { LoveListEntity } from './modules/fan/entity/love-list.entity';
import { AlbumEntity } from './modules/singer/entity/album.entity';
import { SingerAwardEntity } from './modules/singer/entity/singer-award.entity';
import { SingerEntity } from './modules/singer/entity/singer.entity';
import { TrackListEntity } from './modules/singer/entity/track-list.entity';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'postgres',
      host: 'tiny.db.elephantsql.com',
      port: 5432,
      username: 'ntobcmlt',
      password: '4tHP4XwsYxEER_Ed31uKKdsi3eBCzJ9p',
      database: 'ntobcmlt',
      entities: [
        AwardEntity,
        FanEntity,
        LoveListEntity,
        AlbumEntity,
        SingerAwardEntity,
        SingerEntity,
        TrackListEntity,
      ],
      synchronize: true,
      ssl: true,
    }),
    AuthModule,
    FanModule,
    SingerModule,
    AwardModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
