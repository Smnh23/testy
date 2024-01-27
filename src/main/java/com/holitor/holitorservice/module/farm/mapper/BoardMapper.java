package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.BoardMapperConfig;
import com.holitor.holitorservice.module.farm.model.Board;
import com.holitor.holitorservice.module.farm.model.Garden;
import com.holitor.holitorservice.module.farm.model.dto.BoardDto;
import com.holitor.holitorservice.module.farm.repository.GardenRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = BoardMapperConfig.class, componentModel = "spring")
public abstract class BoardMapper {

  @Autowired GardenRepository gardenRepository;

  /* ------------------------- Abstract methods ------------------------- */
  
  @InheritConfiguration(name = "mapBoard")
  public abstract BoardDto toDto(Board input);

  @InheritConfiguration(name = "mapBoardDto")
  public abstract Board toEntity(BoardDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToGarden")
  public final Garden idToGarden(long id) { return gardenRepository.findById(id).get(); }

}