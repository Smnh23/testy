package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Vegetable;
import com.holitor.holitorservice.module.farm.model.dto.VegetableDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface VegetableMapperConfig {

  @Mapping(target = "idBotanicalFamily", source = "botanicalFamily.id")
  public void mapVegetable(Vegetable input, @MappingTarget VegetableDto output);

  @Mapping(target = "botanicalFamily", source = "idBotanicalFamily", qualifiedByName = "idToBotanicalFamily")
  public void mapVegetableDto(VegetableDto input, @MappingTarget Vegetable output);

}