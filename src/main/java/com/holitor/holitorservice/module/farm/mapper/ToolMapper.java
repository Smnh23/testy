package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.Tool;
import com.holitor.holitorservice.module.farm.model.dto.ToolDto;

import org.mapstruct.Mapper;

@Mapper(uses = FarmInputMapper.class, componentModel = "spring")
public interface ToolMapper {

  public ToolDto toDto(Tool input);

  public Tool toEntity(ToolDto input);

}