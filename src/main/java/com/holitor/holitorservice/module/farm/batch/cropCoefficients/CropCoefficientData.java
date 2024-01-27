package com.holitor.holitorservice.module.farm.batch.cropCoefficients;

import lombok.Data;

@Data
public class CropCoefficientData {
  
  private String id;
  private String idVegetable;
  private String numPeriod;
  private String startPeriod;
  private String endPeriod;
  private String value;

}