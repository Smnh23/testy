package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Board;
import com.holitor.holitorservice.module.farm.model.dto.BoardDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface BoardMapperConfig {
  
  @Mapping(target = "idGarden", source = "garden.id")
  public void mapBoard(Board input, @MappingTarget BoardDto output);

  @Mapping(target = "garden", source = "idGarden", qualifiedByName = "idToGarden")
  public void mapBoardDto(BoardDto input, @MappingTarget Board output);
  
}