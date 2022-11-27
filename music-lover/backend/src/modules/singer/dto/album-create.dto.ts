import { ApiProperty } from '@nestjs/swagger';
import { IsNotEmpty, IsString, IsUrl } from 'class-validator';

export class AlbumCreateDto {
  @ApiProperty()
  @IsNotEmpty()
  @IsString()
  readonly name: string;

  @ApiProperty()
  @IsNotEmpty()
  @IsUrl()
  readonly link: string;

  @ApiProperty()
  @IsNotEmpty()
  @IsUrl()
  readonly imageUrl: string;
}
