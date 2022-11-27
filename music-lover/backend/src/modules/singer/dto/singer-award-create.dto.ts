import { ApiProperty } from '@nestjs/swagger';
import { IsNotEmpty, IsNumber, IsString } from 'class-validator';

export class SingerAwardCreateDto {
  @ApiProperty()
  @IsNotEmpty()
  @IsNumber()
  readonly year: number;

  @ApiProperty()
  @IsNotEmpty()
  @IsString()
  readonly category: string;

  @ApiProperty()
  @IsNotEmpty()
  @IsString()
  readonly awardId: string;
}
