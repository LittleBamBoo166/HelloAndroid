import { Injectable } from '@nestjs/common';
import { AlbumCreateDto } from './dto/album-create.dto';
import { AlbumUpdateDto } from './dto/album-update.dto';
import { TrackListCreateDto } from './dto/track-list-create.dto';
import { TrackListUpdateDto } from './dto/track-list-update.dto';
import { AlbumEntity } from './entity/album.entity';
import { AlbumRepository } from './repository/album.repository';
import { TrackListRepository } from './repository/track-list.repository';

@Injectable()
export class AlbumService {
  constructor(
    private albumRepository: AlbumRepository,
    private trackListRepository: TrackListRepository,
  ) {}

  async getNumberOfSongs(id: string) {
    const numberOfSongs = await this.albumRepository.getNumberOfSongs(id);
    return {
      numberOfSongs: numberOfSongs,
    };
  }

  async getOneById(id: string): Promise<AlbumEntity> {
    return this.albumRepository.getOneById(id);
  }

  async getMany(singerId: string): Promise<AlbumEntity[]> {
    return this.albumRepository.getMany(singerId);
  }

  async create(singerId: string, dto: AlbumCreateDto) {
    const entity = this.albumRepository.create(dto, singerId);
    await this.albumRepository.save(entity);
    return {
      id: entity.id,
    };
  }

  async update(id: string, dto: AlbumUpdateDto) {
    await this.albumRepository.update(id, dto);
  }

  async deleteById(id: string) {
    await this.albumRepository.deleteById(id);
    return {
      id: id,
    };
  }

  async addTrack(albumId: string, dto: TrackListCreateDto) {
    const entity = this.trackListRepository.create(dto, albumId);
    await this.trackListRepository.save(entity);
    return {
      id: entity.id,
    };
  }

  async updateTrack(id: string, dto: TrackListUpdateDto) {
    return this.trackListRepository.update(id, dto);
  }

  async removeTrack(id: string) {
    await this.trackListRepository.deleteById(id);
    return {
      id: id,
    };
  }
}
