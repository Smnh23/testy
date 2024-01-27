package com.holitor.holitorservice.module.farm.model.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.holitor.holitorservice.module.farm.model.Vegetable.Irrigation;

import lombok.Data;

@Data
public class VegetableDto {

  private @NotNull long id;
  private @NotNull long idBotanicalFamily;
  private @Size(min=1, max=25) String name;
  private @Size(min=1, max=100) String taxonomy;
  private @Size(min=7, max=7) String color;
  private @Min(0) double spacingPlant;
  private @Min(0) double spacingRank;
  private @Min(0) double yield;
  private @Min(0) double exportN;
  private @Min(0) double exportP;
  private @Min(0) double exportK;
  private @Min(0) double fertiN;
  private @Min(0) double fertiP;
  private @Min(0) double fertiK;
  private @Min(0) double price;
  private boolean piece;
  private @Size(min=1, max=12) String planning;
  private Irrigation irrigation;
  private List<CropCoefficientDto> kcs;
  private List<VarietyDto> varieties;

}