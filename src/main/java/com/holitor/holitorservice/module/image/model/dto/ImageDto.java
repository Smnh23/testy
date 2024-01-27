package com.holitor.holitorservice.module.image.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class ImageDto {
  
  private @NotNull long id;
  private @Pattern(regexp="[^\\s]+(\\.(?i)(jpg|jpeg|png|notype))$") String name;
  private @Pattern(regexp="^image/jpg$|^image/jpeg$|^image/png$|^notype$") String type;
  private @Size(max=1048576) byte[] pic;

}