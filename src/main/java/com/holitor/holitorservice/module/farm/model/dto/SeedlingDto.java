package com.holitor.holitorservice.module.farm.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Data
public class SeedlingDto {

  private @NotNull long id;
  private long predictedQuantity;
  private long actualQuantity;
  private @Null Date date;
  private long duration;
  
}