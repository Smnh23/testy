package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.VarietyMapperConfig;
import com.holitor.holitorservice.module.farm.model.Variety;
import com.holitor.holitorservice.module.farm.model.Vegetable;
import com.holitor.holitorservice.module.farm.model.dto.VarietyDto;
import com.holitor.holitorservice.module.farm.repository.VegetableRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = VarietyMapperConfig.class, componentModel = "spring")
public abstract class VarietyMapper {

  @Autowired VegetableRepository vegetableRepository;
  
  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapVariety")
  public abstract VarietyDto toDto(Variety input);

  @InheritConfiguration(name = "mapVarietyDto")
  public abstract Variety toEntity(VarietyDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToVegetable")
  public final Vegetable idToVegetable(long id) { return vegetableRepository.findById(id).get(); }

}