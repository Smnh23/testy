package com.holitor.holitorservice.module.farm.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PesticideDto extends FarmInputDto {
  
  private boolean ab;

}