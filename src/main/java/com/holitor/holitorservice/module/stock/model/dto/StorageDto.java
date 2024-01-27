package com.holitor.holitorservice.module.stock.model.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.holitor.holitorservice.module.stock.model.Storage.Action;

import lombok.Data;

@Data
public class StorageDto {
  
  private @NotNull long id;
  private @NotNull long idProduct;
  private Action action;
  private Date date;
  private @Min(0) double quantity;
  private @Min(0) double stock;
  private @Min(0) double shop;

}