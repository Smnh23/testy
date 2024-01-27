package com.holitor.holitorservice.module.farm.model.dto;

import com.holitor.holitorservice.module.stock.model.dto.StorageDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class HarvestDto extends StorageDto {
  
  private ProductionKeyDto idProduction;
  private long duration;

}