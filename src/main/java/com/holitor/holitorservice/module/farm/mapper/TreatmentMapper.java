package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.TreatmentMapperConfig;
import com.holitor.holitorservice.module.farm.model.Treatment;
import com.holitor.holitorservice.module.farm.model.Pesticide;
import com.holitor.holitorservice.module.farm.model.dto.TreatmentDto;
import com.holitor.holitorservice.module.farm.repository.PesticideRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = TreatmentMapperConfig.class, componentModel = "spring")
public abstract class TreatmentMapper {

  @Autowired PesticideRepository pesticideRepository;

  @InheritConfiguration(name = "mapTreatment")
  public abstract TreatmentDto toDto(Treatment input);

  @InheritConfiguration(name = "mapTreatmentDto")
  public abstract Treatment toEntity(TreatmentDto input);
  
  /* ------------------------- Final methods ------------------------- */

  @Named("idToPesticide")
  public final Pesticide idToPesticide(long id) { return pesticideRepository.findById(id).get(); }
  
}