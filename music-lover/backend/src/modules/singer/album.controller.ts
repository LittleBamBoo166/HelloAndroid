import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  Post,
} from '@nestjs/common';
import { ApiBody, ApiTags } from '@nestjs/swagger';
import { AlbumService } from './album.service';
import { AlbumUpdateDto } from './dto/album-update.dto';
import { TrackListCreateDto } from './dto/track-list-create.dto';
import { TrackListUpdateDto } from './dto/track-list-update.dto';

@ApiTags('album')
@Controller('albums')
export class AlbumController {
  constructor(private albumService: AlbumService) {}

  @Get(':id')
  async getAlbumById(@Param('id') id: string) {
    return this.albumService.getOneById(id);
  }

  @Get(':id/number-of-songs')
  async getNumberOfSongs(@Param('id') id: string) {
    return this.albumService.getNumberOfSongs(id);
  }

  @ApiBody({ type: AlbumUpdateDto })
  @Patch(':id')
  async updateAlbum(@Param('id') id: string, @Body() data: AlbumUpdateDto) {
    await this.albumService.update(id, data);
  }

  @Delete(':id')
  async removeAlbum(@Param('id') id: string) {
    await this.albumService.deleteById(id);
  }

  @ApiBody({ type: TrackListCreateDto })
  @Post(':id/tracks')
  async addTrack(
    @Param('id') albumId: string,
    @Body() data: TrackListCreateDto,
  ) {
    return this.albumService.addTrack(albumId, data);
  }

  @ApiBody({ type: TrackListUpdateDto })
  @Patch('tracks/:id')
  async updateTrack(
    @Param('id') trackId: string,
    @Body() data: TrackListUpdateDto,
  ) {
    return this.albumService.updateTrack(trackId, data);
  }

  @Delete('tracks/:id')
  async deleteTrack(@Param('id') trackId: string) {
    return this.albumService.removeTrack(trackId);
  }
}
