package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Serie;
import com.holitor.holitorservice.module.farm.model.dto.SerieDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface SerieMapperConfig {
  
  @Mapping(target = "idBoard", source = "board.id")
  public void mapSerie(Serie input, @MappingTarget SerieDto output);

  @Mapping(target = "board", source = "idBoard", qualifiedByName = "idToBoard")
  public void mapSerieDto(SerieDto input, @MappingTarget Serie output);
  
}