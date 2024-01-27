package com.holitor.holitorservice.module.farm.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CultivationPlanningDto {
  
  private @NotNull long id;
  private Date begin;
  private Date harvestBegin;
  private Date harvestEnd;
  
}