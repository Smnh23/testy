package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Task;
import com.holitor.holitorservice.module.farm.model.dto.TaskDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

@MapperConfig()
public interface TaskMapperConfig {

  @Mappings({

    @Mapping(target = "idType", source = "type.id"),
    @Mapping(target = "idsTools", source = "tools", qualifiedByName = "toolsToIds")

  })
  public void mapTask(Task input, @MappingTarget TaskDto output);

  @Mappings({
  
    @Mapping(target = "type", source = "idType", qualifiedByName = "idToType"),
    @Mapping(target = "tools", source = "idsTools", qualifiedByName = "idsToTools")
  
  }) public void mapTaskDto(TaskDto input, @MappingTarget Task output);
  
}