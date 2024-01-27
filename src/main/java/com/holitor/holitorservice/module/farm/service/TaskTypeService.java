package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.TaskTypeMapper;
import com.holitor.holitorservice.module.farm.model.TaskType;
import com.holitor.holitorservice.module.farm.model.dto.TaskTypeDto;
import com.holitor.holitorservice.module.farm.repository.TaskTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskTypeService {
  
  private static final String TASK_TYPE_NOT_FOUND = "TaskType %s not found !";

  private @Autowired TaskTypeRepository taskTypeRepository;
  private @Autowired TaskTypeMapper taskTypeMapper;
  
  @Transactional(readOnly = false)
  public TaskTypeDto addTaskType(TaskTypeDto taskTypeDto) { 
    TaskType taskType = this.taskTypeMapper.toEntity(taskTypeDto);
    return this.taskTypeMapper.toDto(this.taskTypeRepository.save(taskType)); 
  }
  
  @Transactional(readOnly = true)
  public List<TaskTypeDto> getTaskTypes() { 
    List<TaskType> taskTypes = (List<TaskType>) this.taskTypeRepository.findAll();
    return taskTypes.stream().map(taskType -> this.taskTypeMapper.toDto(taskType)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public TaskTypeDto updateTaskType(TaskTypeDto taskTypeDto) { 
    TaskType taskType = this.taskTypeMapper.toEntity(taskTypeDto);
    if (!this.taskTypeRepository.existsById(taskType.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(TASK_TYPE_NOT_FOUND, taskType.getId()));
    return this.taskTypeMapper.toDto(this.taskTypeRepository.save(taskType)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<TaskTypeDto> deleteTaskType(long idTaskType) { 
    if (!this.taskTypeRepository.existsById(idTaskType)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(TASK_TYPE_NOT_FOUND, idTaskType));
    this.taskTypeRepository.deleteById(idTaskType);
    return this.getTaskTypes();
  }

}