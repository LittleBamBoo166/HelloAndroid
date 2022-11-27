import { SingerAwardEntity } from 'src/modules/singer/entity/singer-award.entity';
import { Column, Entity, PrimaryGeneratedColumn, OneToMany } from 'typeorm';

@Entity()
export class AwardEntity {
  @PrimaryGeneratedColumn()
  id: string;

  @Column()
  name: string;

  @Column()
  location: string;

  @Column()
  country: string;

  @Column()
  website: string;

  @OneToMany(() => SingerAwardEntity, (singerAward) => singerAward.award)
  singers: SingerAwardEntity[];

  constructor(id: string) {
    this.id = id;
  }
}
