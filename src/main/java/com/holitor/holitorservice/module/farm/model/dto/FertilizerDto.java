package com.holitor.holitorservice.module.farm.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class FertilizerDto extends FarmInputDto {
  
  private double nitrogen;
  private double phosphorus;
  private double potassium;
  private boolean ab;

}