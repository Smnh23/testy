package com.holitor.holitorservice.module.shop.model.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class SalePointDto {
  
  private @NotNull long id;
  private boolean actived;
  private @Size(min=1, max=50) String place;
  private @Size(min=5, max=5) String zipCode;
  private @Size(min=1, max=50) String city;
  private @Min(0) @Max(6) int day;
  private @Size(min=8, max=8) String startTime;
  private @Size(min=8, max=8) String endTime;
  private @Min(-90) @Max(90) double latitude;
  private @Min(-180) @Max(180) double longitude;
  
}