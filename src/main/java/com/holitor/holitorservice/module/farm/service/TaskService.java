package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.TaskMapper;
import com.holitor.holitorservice.module.farm.model.Task;
import com.holitor.holitorservice.module.farm.model.dto.TaskDto;
import com.holitor.holitorservice.module.farm.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {
  
  private static final String TASK_NOT_FOUND = "Task %s not found !";

  private @Autowired TaskRepository taskRepository;
  private @Autowired TaskMapper taskMapper;
  
  @Transactional(readOnly = false)
  public TaskDto addTask(TaskDto taskDto) { 
    Task task = this.taskMapper.toEntity(taskDto);
    return this.taskMapper.toDto(this.taskRepository.save(task)); 
  }
  
  @Transactional(readOnly = true)
  public List<TaskDto> getTasks() { 
    List<Task> tasks = (List<Task>) this.taskRepository.findAll();
    return tasks.stream().map(task -> this.taskMapper.toDto(task)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public TaskDto updateTask(TaskDto taskDto) { 
    Task task = this.taskMapper.toEntity(taskDto);
    if (!this.taskRepository.existsById(task.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(TASK_NOT_FOUND, task.getId()));
    return this.taskMapper.toDto(this.taskRepository.save(task)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<TaskDto> deleteTask(long idTask) { 
    if (!this.taskRepository.existsById(idTask)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(TASK_NOT_FOUND, idTask));
    this.taskRepository.deleteById(idTask);
    return this.getTasks();
  }

}