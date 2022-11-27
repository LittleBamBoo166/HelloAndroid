import { ApiProperty } from '@nestjs/swagger';
import { IsOptional, IsString, IsUrl } from 'class-validator';

export class AwardUpdateDto {
  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsString()
  readonly name?: string;

  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsString()
  readonly location?: string;

  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsString()
  readonly country?: string;

  @ApiProperty({ nullable: true })
  @IsOptional()
  @IsUrl()
  readonly website?: string;
}
