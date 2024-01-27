package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.FertilizationMapperConfig;
import com.holitor.holitorservice.module.farm.model.Fertilization;
import com.holitor.holitorservice.module.farm.model.Fertilizer;
import com.holitor.holitorservice.module.farm.model.dto.FertilizationDto;
import com.holitor.holitorservice.module.farm.repository.FertilizerRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = FertilizationMapperConfig.class, componentModel = "spring")
public abstract class FertilizationMapper {

  @Autowired FertilizerRepository fertilizerRepository;

  @InheritConfiguration(name = "mapFertilization")
  public abstract FertilizationDto toDto(Fertilization input);

  @InheritConfiguration(name = "mapFertilizationDto")
  public abstract Fertilization toEntity(FertilizationDto input);
  
  /* ------------------------- Final methods ------------------------- */

  @Named("idToFertilizer")
  public final Fertilizer idToFertilizer(long id) { return fertilizerRepository.findById(id).get(); }
  
}