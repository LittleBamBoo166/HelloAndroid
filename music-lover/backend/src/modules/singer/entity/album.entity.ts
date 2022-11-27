import {
  Column,
  Entity,
  PrimaryGeneratedColumn,
  ManyToOne,
  OneToMany,
} from 'typeorm';
import { SingerEntity } from './singer.entity';
import { TrackListEntity } from './track-list.entity';

@Entity()
export class AlbumEntity {
  @PrimaryGeneratedColumn()
  id: string;

  @Column()
  name: string;

  @Column()
  link: string;

  @Column()
  imageUrl: string;

  @ManyToOne(() => SingerEntity, (singer) => singer.albums)
  singer?: SingerEntity;

  @OneToMany(() => TrackListEntity, (trackList) => trackList.album, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
  })
  trackList?: TrackListEntity[];

  constructor(id: string) {
    this.id = id;
  }
}
