package com.holitor.holitorservice.module.farm.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CropCoefficientDto {
  
  private @NotNull long id;
  private @Min(0) int numPeriod;
  private @Size(min=1, max=50) String startPeriod;
  private @Size(min=1, max=50) String endPeriod;
  private @Min(0) double value;

}