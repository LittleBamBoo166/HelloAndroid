// export class SingerUpdateDto {}
import { ApiProperty } from '@nestjs/swagger';
import { IsDateString, IsOptional, IsNumber, IsString } from 'class-validator';

export class SingerUpdateDto {
  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsString()
  readonly name?: string;

  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsString()
  readonly address?: string;

  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsNumber()
  readonly yearActivate?: number;
}
