package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Fertilization;
import com.holitor.holitorservice.module.farm.model.dto.FertilizationDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface FertilizationMapperConfig {

  @Mapping(target = "idFertilizer", source = "fertilizer.id")
  public void mapFertilization(Fertilization input, @MappingTarget FertilizationDto output);

  @Mapping(target = "fertilizer", source = "idFertilizer", qualifiedByName = "idToFertilizer")
  public void mapFertilizationDto(FertilizationDto input, @MappingTarget Fertilization output);

}