package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.AnalysisMapperConfig;
import com.holitor.holitorservice.module.farm.model.Analysis;
import com.holitor.holitorservice.module.farm.model.Garden;
import com.holitor.holitorservice.module.farm.model.dto.AnalysisDto;
import com.holitor.holitorservice.module.farm.repository.GardenRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = AnalysisMapperConfig.class, componentModel = "spring")
public abstract class AnalysisMapper {

  @Autowired GardenRepository gardenRepository;

  /* ------------------------- Abstract methods ------------------------- */
  
  @InheritConfiguration(name = "mapAnalysis")
  public abstract AnalysisDto toDto(Analysis input);

  @InheritConfiguration(name = "mapAnalysisDto")
  public abstract Analysis toEntity(AnalysisDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToGarden")
  public final Garden idToGarden(long id) { return gardenRepository.findById(id).get(); }

}