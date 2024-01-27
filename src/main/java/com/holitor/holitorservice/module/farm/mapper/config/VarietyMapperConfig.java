package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Variety;
import com.holitor.holitorservice.module.farm.model.dto.VarietyDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface VarietyMapperConfig {
 
  @Mapping(target = "idVegetable", source = "vegetable.id")
  public void mapVariety(Variety input, @MappingTarget VarietyDto output);

  @Mapping(target = "vegetable", source = "idVegetable", qualifiedByName = "idToVegetable")
  public void mapVarietyDto(VarietyDto input, @MappingTarget Variety output);

}