package com.holitor.holitorservice.module.farm.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProductionKeyDto {

  private @NotNull long serieId;
  private @NotNull long varietyId;
  
}
