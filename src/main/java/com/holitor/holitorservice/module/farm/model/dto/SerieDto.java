package com.holitor.holitorservice.module.farm.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.holitor.holitorservice.module.farm.model.Vegetable.Irrigation;

import lombok.Data;

@Data
public class SerieDto {
  
  private @NotNull long id;
  private @NotNull long idBoard;
  private double spacingPlant;
  private double spacingRank;
  private double yield;
  private double price;
  private boolean sown;
  private CultivationPlanningDto planning;
  private Irrigation irrigation;
  private List<ProductionDto> productions;
  private List<FertilizationDto> fertilizations;
  private List<TreatmentDto> treatments;
  private List<TaskDto> tasks;

}