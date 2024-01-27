package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.TaskType;
import com.holitor.holitorservice.module.farm.model.dto.TaskTypeDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskTypeMapper {

  public TaskTypeDto toDto(TaskType input);

  public TaskType toEntity(TaskTypeDto input);

}