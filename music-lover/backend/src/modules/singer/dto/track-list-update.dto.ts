// export class TrackListUpdateDto {}
import { ApiProperty } from '@nestjs/swagger';
import { IsArray, IsOptional, IsNumber, IsString } from 'class-validator';

export class TrackListUpdateDto {
  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsString()
  readonly name?: string;

  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsNumber()
  readonly length?: number;

  @ApiProperty({ isArray: true })
  @IsOptional()
  @IsArray()
  readonly writer?: string[];
}
