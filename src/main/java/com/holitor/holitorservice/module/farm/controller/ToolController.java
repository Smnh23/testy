package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.ToolDto;
import com.holitor.holitorservice.module.farm.service.ToolService;

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
public class ToolController {
  
  private @Autowired ToolService toolService;

  @PostMapping("/tool")
  @ResponseStatus(HttpStatus.CREATED)
  public ToolDto addTool(@RequestBody @Valid ToolDto toolDto) { return this.toolService.addTool(toolDto); }

  @GetMapping("/tools")
  @ResponseStatus(HttpStatus.OK)
  public List<ToolDto> getTools() { return this.toolService.getTools(); }

  @PutMapping("/tool")
  @ResponseStatus(HttpStatus.OK)
  public ToolDto updateTool(@RequestBody @Valid ToolDto toolDto) {
    try { toolDto = this.toolService.updateTool(toolDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return toolDto;
  }

  @DeleteMapping("/tool/{idTool}")
  @ResponseStatus(HttpStatus.OK)
  public List<ToolDto> deleteTool(@PathVariable long idTool) {
    List<ToolDto> tools = null;
    try { tools = this.toolService.deleteTool(idTool); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return tools;
  }

}