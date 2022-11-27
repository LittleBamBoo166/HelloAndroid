import { AwardEntity } from 'src/modules/award/entity/award.entity';
import { Column, Entity, PrimaryGeneratedColumn, ManyToOne } from 'typeorm';
import { SingerEntity } from './singer.entity';

@Entity()
export class SingerAwardEntity {
  @PrimaryGeneratedColumn()
  id: string;

  @Column()
  year: number;

  @Column()
  category: string;

  @ManyToOne(() => SingerEntity, (singer) => singer.awards)
  singer: SingerEntity;

  @ManyToOne(() => AwardEntity, (award) => award.singers)
  award: AwardEntity;
}
