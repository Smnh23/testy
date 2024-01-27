package com.holitor.holitorservice.module.shop.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ItemDto {
  
  private @NotNull long id;
  private @NotNull long idOrder;
  private @NotNull long idProduct;
  private @Min(0) int quantity;
  private @Min(0) double price;

}