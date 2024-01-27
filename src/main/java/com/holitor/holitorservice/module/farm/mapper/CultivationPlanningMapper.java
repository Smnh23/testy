package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.CultivationPlanning;
import com.holitor.holitorservice.module.farm.model.dto.CultivationPlanningDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CultivationPlanningMapper {

  public CultivationPlanningDto toDto(CultivationPlanning input);
  
  public CultivationPlanning toEntity(CultivationPlanningDto input);
  
}