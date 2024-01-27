package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.ToolMapper;
import com.holitor.holitorservice.module.farm.model.Tool;
import com.holitor.holitorservice.module.farm.model.dto.ToolDto;
import com.holitor.holitorservice.module.farm.repository.ToolRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ToolService {
  
  private static final String TOOL_NOT_FOUND = "Tool %s not found !";

  private @Autowired ToolRepository toolRepository;
  private @Autowired ToolMapper toolMapper;
  
  @Transactional(readOnly = false)
  public ToolDto addTool(ToolDto toolDto) { 
    Tool tool = this.toolMapper.toEntity(toolDto);
    return this.toolMapper.toDto(this.toolRepository.save(tool)); 
  }
  
  @Transactional(readOnly = true)
  public List<ToolDto> getTools() { 
    List<Tool> tools = (List<Tool>) this.toolRepository.findAll();
    return tools.stream().map(tool -> this.toolMapper.toDto(tool)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public ToolDto updateTool(ToolDto toolDto) { 
    Tool tool = this.toolMapper.toEntity(toolDto);
    if (!this.toolRepository.existsById(tool.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(TOOL_NOT_FOUND, tool.getId()));
    return this.toolMapper.toDto(this.toolRepository.save(tool)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<ToolDto> deleteTool(long idTool) { 
    if (!this.toolRepository.existsById(idTool)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(TOOL_NOT_FOUND, idTool));
    this.toolRepository.deleteById(idTool);
    return this.getTools();
  }

}