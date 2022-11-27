import { Column, Entity, PrimaryGeneratedColumn, OneToMany } from 'typeorm';
import { LoveListEntity } from './love-list.entity';

@Entity()
export class FanEntity {
  @PrimaryGeneratedColumn()
  id: string;

  @Column()
  name: string;

  @Column()
  password: string;

  @OneToMany(() => LoveListEntity, (loveList) => loveList.fan)
  loveList: LoveListEntity[];
}
