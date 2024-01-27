package com.holitor.holitorservice.module.farm.model.dto;

import com.holitor.holitorservice.module.farm.model.FarmInput.FarmInputType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class FarmInputDto {
  
  private @NotNull long id;
  private @Size(min=1, max=25) String name;
  private @Size(min=1, max=25) String brand;
  private @Min(0) double price;
  private FarmInputType type;

}