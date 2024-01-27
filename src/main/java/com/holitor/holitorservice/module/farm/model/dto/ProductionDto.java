package com.holitor.holitorservice.module.farm.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductionDto {

  private ProductionKeyDto id;
  private @NotNull long idSerie;
  private @NotNull long idVariety;
  private SeedlingDto seedling;
 
}