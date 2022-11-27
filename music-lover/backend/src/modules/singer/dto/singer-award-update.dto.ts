import { ApiProperty } from '@nestjs/swagger';
import { IsOptional, IsNumber, IsString } from 'class-validator';

export class SingerAwardUpdateDto {
  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsNumber()
  readonly year?: number;

  @ApiProperty()
  @IsOptional()
  @IsString()
  readonly category?: string;
}
