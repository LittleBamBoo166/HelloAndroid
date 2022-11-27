import { ApiProperty } from '@nestjs/swagger';
import { IsDateString, IsOptional, IsString, IsUrl } from 'class-validator';

export class AlbumUpdateDto {
  @ApiProperty()
  @IsOptional()
  @IsString()
  readonly name?: string;

  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsDateString()
  readonly releaseDate?: string;

  @ApiProperty()
  @IsOptional()
  @IsUrl()
  readonly link?: string;

  @ApiProperty()
  @IsOptional()
  @IsUrl()
  readonly imageUrl?: string;
}
