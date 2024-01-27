package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.TaskMapperConfig;
import com.holitor.holitorservice.module.farm.model.Task;
import com.holitor.holitorservice.module.farm.model.TaskType;
import com.holitor.holitorservice.module.farm.model.Tool;
import com.holitor.holitorservice.module.farm.model.dto.TaskDto;
import com.holitor.holitorservice.module.farm.repository.TaskTypeRepository;
import com.holitor.holitorservice.module.farm.repository.ToolRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.IteratorUtils;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = TaskMapperConfig.class, uses = ToolMapper.class, componentModel = "spring")
public abstract class TaskMapper {

  @Autowired TaskTypeRepository taskTypeRepository;
  @Autowired ToolRepository toolRepository;

  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapTask")
  public abstract TaskDto toDto(Task input);

  @InheritConfiguration(name = "mapTaskDto")
  public abstract Task toEntity(TaskDto input);
  
  /* ------------------------- Final methods ------------------------- */

  @Named("idToType")
  public final TaskType idToType(long id) { return taskTypeRepository.findById(id).get(); }

  @Named("idsToTools")
  public final List<Tool> idsToTools(List<Long> ids) { return IteratorUtils.toList(toolRepository.findAllById(ids).iterator()); }

  @Named("toolsToIds")
  public final List<Long> toolsToIds(List<Tool> tools) { return tools.stream().map(Tool::getId).collect(Collectors.toList()); }

}