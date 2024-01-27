package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.Seedling;
import com.holitor.holitorservice.module.farm.model.dto.SeedlingDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeedlingMapper {

  public SeedlingDto toDto(Seedling input);

  public Seedling toEntity(SeedlingDto input);

}