package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.VegetableMapperConfig;
import com.holitor.holitorservice.module.farm.model.BotanicalFamily;
import com.holitor.holitorservice.module.farm.model.Vegetable;
import com.holitor.holitorservice.module.farm.model.dto.VegetableDto;
import com.holitor.holitorservice.module.farm.repository.BotanicalFamilyRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = VegetableMapperConfig.class, uses = VarietyMapper.class, componentModel = "spring")
public abstract class VegetableMapper {

  @Autowired BotanicalFamilyRepository botanicalFamilyRepository;

  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapVegetable")
  public abstract VegetableDto toDto(Vegetable input);
  
  @InheritConfiguration(name = "mapVegetableDto")
  public abstract Vegetable toEntity(VegetableDto input);
  
  /* ------------------------- Final methods ------------------------- */

  @Named("idToBotanicalFamily")
  public final BotanicalFamily idToBotanicalFamily(long id) { return botanicalFamilyRepository.findById(id).get(); }

}