package com.holitor.holitorservice.module.farm.model.dto;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AnalysisDto {
  
  private @NotNull long id;
  private @NotNull long idGarden;
  private Date date;
  private @Min(0) double clay;
  private @Min(0) double silt;
  private @Min(0) double sand;
  private @Min(0) @Max(14) double ph;
  private @Min(0) double nitrogen;
  private @Min(0) double mo;
  private @Min(0) double cec;
  private @Min(0) double saturationRateCEC;
  private @Min(0) double phosphorus;
  private @Min(0) double potassium;
  private @Min(0) double calcium;
  private @Min(0) double magnesium;
  private @Min(0) double sodium;
  private @Min(0) double boron;
  private @Min(0) double copper;
  private @Min(0) double zinc;
  private @Min(0) double manganese;
  private @Min(0) double iron;

}