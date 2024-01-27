package com.holitor.holitorservice.module.shop.model.dto;

import javax.validation.constraints.NotNull;

import com.holitor.holitorservice.module.stock.model.dto.StorageDto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDto extends StorageDto {
  
  private @NotNull long idOrder;

}