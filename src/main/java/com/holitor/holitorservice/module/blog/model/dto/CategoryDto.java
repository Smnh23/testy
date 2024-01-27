package com.holitor.holitorservice.module.blog.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.holitor.holitorservice.module.image.model.dto.ImageDto;

import lombok.Data;

@Data
public class CategoryDto {
  
  private @NotNull long id;
  private @Size(min=7, max=7) String color;
  private @Size(min=1, max=25) String name;
  private @Size(min=1, max=500) String description;
  private ImageDto image;

}