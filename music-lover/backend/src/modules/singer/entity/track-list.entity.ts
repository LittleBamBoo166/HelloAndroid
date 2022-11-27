import { Column, Entity, PrimaryGeneratedColumn, ManyToOne } from 'typeorm';
import { AlbumEntity } from './album.entity';

@Entity()
export class TrackListEntity {
  @PrimaryGeneratedColumn()
  id: string;

  @Column()
  name: string;

  @ManyToOne(() => AlbumEntity, (album) => album.trackList, {
    onDelete: 'CASCADE',
    onUpdate: 'CASCADE',
  })
  album: AlbumEntity;

  @Column()
  length: number;

  @Column('text', { array: true })
  writer: string[];
}
