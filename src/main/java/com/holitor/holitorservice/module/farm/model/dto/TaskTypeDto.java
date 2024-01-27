package com.holitor.holitorservice.module.farm.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class TaskTypeDto {
 
  private @NotNull long id;
  private @Size(min=1, max=25) String name;
  
}