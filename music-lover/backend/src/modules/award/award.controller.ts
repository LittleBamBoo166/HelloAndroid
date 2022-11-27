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
import { AwardService } from './award.service';
import { AwardCreateDto } from './dto/award-create.dto';
import { AwardUpdateDto } from './dto/award-update.dto';
import { AwardEntity } from './entity/award.entity';

@ApiTags('award')
@Controller('awards')
export class AwardController {
  constructor(private awardService: AwardService) {}

  @ApiBody({ type: AwardCreateDto })
  @Post()
  async create(@Body() data: AwardCreateDto) {
    return this.awardService.create(data);
  }

  @ApiBody({ type: AwardUpdateDto })
  @Patch(':id')
  async update(@Param('id') id: string, @Body() data: AwardUpdateDto) {
    return this.awardService.update(id, data);
  }

  @Delete(':id')
  async deleteById(@Param('id') id: string) {
    await this.awardService.deleteById(id);
  }

  @Get()
  async getMany(): Promise<AwardEntity[]> {
    return this.awardService.getMany();
  }

  @Get(':id')
  async getOneById(@Param('id') id: string): Promise<AwardEntity> {
    return this.awardService.getOneById(id);
  }
}
