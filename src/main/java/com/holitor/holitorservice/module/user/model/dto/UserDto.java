package com.holitor.holitorservice.module.user.model.dto;

import javax.validation.constraints.NotNull;

import com.holitor.holitorservice.module.image.model.dto.ImageDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDto extends UserOktaDto {
  
  private @NotNull long id;
  private ImageDto image;

}