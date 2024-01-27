package com.holitor.holitorservice.module.image.mapper;

import com.holitor.holitorservice.module.image.model.Image;
import com.holitor.holitorservice.module.image.model.dto.ImageDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {

  public ImageDto toDto(Image input);

  public Image toEntity(ImageDto input);
  
}