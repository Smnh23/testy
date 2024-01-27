package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Analysis;
import com.holitor.holitorservice.module.farm.model.dto.AnalysisDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface AnalysisMapperConfig {
  
  @Mapping(target = "idGarden", source = "garden.id")
  public void mapAnalysis(Analysis input, @MappingTarget AnalysisDto output);

  @Mapping(target = "garden", source = "idGarden", qualifiedByName = "idToGarden")
  public void mapAnalysisDto(AnalysisDto input, @MappingTarget Analysis output);
  
}