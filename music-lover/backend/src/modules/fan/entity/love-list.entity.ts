import { SingerEntity } from 'src/modules/singer/entity/singer.entity';
import { Column, Entity, PrimaryGeneratedColumn, ManyToOne } from 'typeorm';
import { FanEntity } from './fan.entity';

@Entity()
export class LoveListEntity {
  @PrimaryGeneratedColumn()
  id: string;

  @ManyToOne(() => FanEntity, (fan) => fan.loveList)
  fan: FanEntity;

  @ManyToOne(() => SingerEntity, (singer) => singer.loveList)
  singer: SingerEntity;

  @Column()
  notification: boolean;
}
