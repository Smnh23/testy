package com.holitor.holitorservice.module.farm.model.dto;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class GardenDto {
  
  private @NotNull long id;
  private @NotNull long idParcel;
  private @Size(min=1, max=25) String name;
  private @Min(0) double length;
  private @Min(0) double width;
  private @Min(0) double border;
  private @Min(0) double footPass;
  private List<BoardDto> boards;
  private List<AnalysisDto> analyzes;

}