package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.TaskTypeDto;
import com.holitor.holitorservice.module.farm.service.TaskTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("${url.api}")
public class TaskTypeController {
  
  private @Autowired TaskTypeService taskTypeService;

  @PostMapping("/task-type")
  @ResponseStatus(HttpStatus.CREATED)
  public TaskTypeDto addTaskType(@RequestBody @Valid TaskTypeDto taskTypeDto) { return this.taskTypeService.addTaskType(taskTypeDto); }

  @GetMapping("/task-types")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskTypeDto> getTaskTypes() { return this.taskTypeService.getTaskTypes(); }

  @PutMapping("/task-type")
  @ResponseStatus(HttpStatus.OK)
  public TaskTypeDto updateTaskType(@RequestBody @Valid TaskTypeDto taskTypeDto) {
    try { taskTypeDto = this.taskTypeService.updateTaskType(taskTypeDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return taskTypeDto;
  }

  @DeleteMapping("/task-type/{idTaskType}")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskTypeDto> deleteTaskType(@PathVariable long idTaskType) {
    List<TaskTypeDto> taskTypes = null;
    try { taskTypes = this.taskTypeService.deleteTaskType(idTaskType); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return taskTypes;
  }

}