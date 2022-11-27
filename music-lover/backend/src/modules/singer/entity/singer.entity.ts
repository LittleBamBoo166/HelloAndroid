import { AwardEntity } from 'src/modules/award/entity/award.entity';
import { LoveListEntity } from 'src/modules/fan/entity/love-list.entity';
import { Column, Entity, PrimaryGeneratedColumn, OneToMany } from 'typeorm';
import { AlbumEntity } from './album.entity';
import { SingerAwardEntity } from './singer-award.entity';

@Entity()
export class SingerEntity {
  @PrimaryGeneratedColumn()
  id: string;

  @Column()
  name: string;

  @Column()
  password: string;

  @Column({ nullable: true })
  address?: string;

  @Column({ nullable: true })
  yearActivate?: number;

  @OneToMany(() => SingerAwardEntity, (award) => award.singer)
  awards: AwardEntity[];

  @OneToMany(() => LoveListEntity, (loveList) => loveList.singer)
  loveList: LoveListEntity[];

  @OneToMany(() => AlbumEntity, (album) => album.singer)
  albums: AlbumEntity[];

  constructor(id: string) {
    this.id = id;
  }
}
