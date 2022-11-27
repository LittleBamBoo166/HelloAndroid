import { ApiProperty } from '@nestjs/swagger';
import { IsNotEmpty } from 'class-validator';
import { IsString } from 'class-validator/types/decorator/decorators';

export class FanCreateDto {
  @ApiProperty()
  @IsNotEmpty()
  @IsString()
  readonly name: string;
}
