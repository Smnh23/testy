package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Treatment;
import com.holitor.holitorservice.module.farm.model.dto.TreatmentDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface TreatmentMapperConfig {

  @Mapping(target = "idPesticide", source = "pesticide.id")
  public void mapTreatment(Treatment input, @MappingTarget TreatmentDto output);

  @Mapping(target = "pesticide", source = "idPesticide", qualifiedByName = "idToPesticide")
  public void mapTreatmentDto(TreatmentDto input, @MappingTarget Treatment output);

}