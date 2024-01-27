package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.TaskDto;
import com.holitor.holitorservice.module.farm.service.TaskService;

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
public class TaskController {
  
  private @Autowired TaskService taskService;

  @PostMapping("/task")
  @ResponseStatus(HttpStatus.CREATED)
  public TaskDto addTask(@RequestBody @Valid TaskDto taskDto) { return this.taskService.addTask(taskDto); }
  
  @GetMapping("/tasks")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskDto> getTasks() { return this.taskService.getTasks(); }

  @PutMapping("/task")
  @ResponseStatus(HttpStatus.OK)
  public TaskDto updateTask(@RequestBody @Valid TaskDto taskDto) {
    try { taskDto = this.taskService.updateTask(taskDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return taskDto;
  }

  @DeleteMapping("/task/{idTask}")
  @ResponseStatus(HttpStatus.OK)
  public List<TaskDto> deleteTask(@PathVariable long idTask) {
    List<TaskDto> tasks = null;
    try { tasks = this.taskService.deleteTask(idTask); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return tasks;
  }

}