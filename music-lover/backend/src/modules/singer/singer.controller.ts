import { Body, Controller, Get, Param, Patch, Post, Delete } from '@nestjs/common';
import { ApiBody, ApiTags } from '@nestjs/swagger';
import { AlbumService } from './album.service';
import { AlbumCreateDto } from './dto/album-create.dto';
import { SingerAwardCreateDto } from './dto/singer-award-create.dto';
import { SingerAwardUpdateDto } from './dto/singer-award-update.dto';
import { SingerCreateDto } from './dto/singer-create.dto';
import { SingerLoginDto } from './dto/singer-login.dto';
import { SingerUpdateDto } from './dto/singer-update.dto';
import { SingerEntity } from './entity/singer.entity';
import { SingerService } from './singer.service';

@ApiTags('singer')
@Controller('singers')
export class SingerController {
  constructor(
    private singerService: SingerService,
    private albumService: AlbumService,
  ) {}

  @ApiBody({ type: SingerLoginDto })
  @Post('login')
  async login(@Body() data) {
    return this.singerService.login(data.name, data.password);
  }

  @Get(':id')
  async getOneById(@Param('id') id: string): Promise<SingerEntity> {
    return this.singerService.getOneById(id);
  }

  @Get(':id/number-of-albums')
  async getNumberOfAlbums(@Param('id') id: string) {
    return this.singerService.getNumberOfAlbums(id);
  }

  @ApiBody({ type: SingerCreateDto })
  @Post()
  async create(@Body() data: SingerCreateDto) {
    return this.singerService.create(data);
  }

  @ApiBody({ type: SingerUpdateDto })
  @Patch(':id')
  async update(@Body() data: SingerUpdateDto, @Param('id') id: string) {
    await this.singerService.update(id, data);
  }

  @Get(':id/awards')
  async getAwards(@Param('id') id: string) {
    return this.singerService.getAwards(id);
  }

  @ApiBody({ type: SingerAwardCreateDto })
  @Post(':id/awards')
  async addAward(
    @Param('id') singerId: string,
    @Body() data: SingerAwardCreateDto,
  ) {
    return this.singerService.addAward(data, singerId);
  }

  @ApiBody({ type: SingerAwardUpdateDto })
  @Patch('awards/:id')
  async updateAward(
    @Param('id') id: string,
    @Body() data: SingerAwardUpdateDto,
  ) {
    await this.singerService.updateAward(id, data);
  }

  @Delete('awards/:id')
  async removeAward(@Param('id') id: string) {
    await this.singerService.removeAward(id);
  }

  @Get(':id/albums')
  async getAlbums(@Param('id') id: string) {
    return this.albumService.getMany(id);
  }

  @ApiBody({ type: AlbumCreateDto })
  @Post(':id/albums')
  async addAlbum(@Param('id') singerId: string, @Body() data: AlbumCreateDto) {
    return this.albumService.create(singerId, data);
  }
}
