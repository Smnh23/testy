package com.holitor.holitorservice.module.stock.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.holitor.holitorservice.module.image.model.dto.ImageDto;

import lombok.Data;

@Data
public class ProductDto {
  
  private @NotNull long id;
  private @NotNull long idUnit;
  private @Null long idVariety;
  private @Size(min=1, max=25) String name;
  private @Size(min=1, max=500) String description;
  private @Min(0) double price;
  private ImageDto image;

}