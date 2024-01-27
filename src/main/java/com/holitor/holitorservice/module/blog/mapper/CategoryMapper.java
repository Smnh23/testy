package com.holitor.holitorservice.module.blog.mapper;

import com.holitor.holitorservice.module.blog.model.Category;
import com.holitor.holitorservice.module.blog.model.dto.CategoryDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
  
  public CategoryDto toDto(Category input);

  public Category toEntity(CategoryDto input);

}