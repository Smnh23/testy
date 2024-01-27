package com.holitor.holitorservice.module.farm.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class BoardDto {
  
  private @NotNull long id;
  private @NotNull long idGarden;
  private @Size(min=1, max=25) String name;
  private @Min(0) double length;
  private @Min(0) double width;

}