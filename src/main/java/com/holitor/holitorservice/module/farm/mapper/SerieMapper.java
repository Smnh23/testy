package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.SerieMapperConfig;
import com.holitor.holitorservice.module.farm.model.Board;
import com.holitor.holitorservice.module.farm.model.Serie;
import com.holitor.holitorservice.module.farm.model.dto.SerieDto;
import com.holitor.holitorservice.module.farm.repository.BoardRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = SerieMapperConfig.class, uses = { CultivationPlanningMapper.class, ProductionMapper.class, FertilizationMapper.class, TreatmentMapper.class, TaskMapper.class }, componentModel = "spring")
public abstract class SerieMapper {

  @Autowired BoardRepository boardRepository;

  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapSerie")
  public abstract SerieDto toDto(Serie input);

  @InheritConfiguration(name = "mapSerieDto")
  public abstract Serie toEntity(SerieDto input);
  
  /* ------------------------- Final methods ------------------------- */

  @Named("idToBoard")
  public final Board idToBoard(long id) { return boardRepository.findById(id).get(); }

}