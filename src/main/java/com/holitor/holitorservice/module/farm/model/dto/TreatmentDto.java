package com.holitor.holitorservice.module.farm.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import lombok.Data;

@Data
public class TreatmentDto {
  
  private @NotNull long id;
  private @NotNull long idPesticide;
  private double predictedQuantity;
  private double actualQuantity;
  private Date predictedDate;
  private @Null Date actualDate;
  private long duration;

}