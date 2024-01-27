package com.holitor.holitorservice.module.farm.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class VarietyDto {

  private @NotNull long id;
  private @NotNull long idVegetable;
  private @Size(min=1, max=25) String name;
  private @Size(min=1, max=12) String planning;
  private @Min(0) double price;
  private boolean hybridF1;

}