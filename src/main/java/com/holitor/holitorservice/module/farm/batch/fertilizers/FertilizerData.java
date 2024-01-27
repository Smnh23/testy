package com.holitor.holitorservice.module.farm.batch.fertilizers;

import lombok.Data;

@Data
public class FertilizerData {
  
  private String id;
  private String name;
  private String brand;
  private String price;
  private String nitrogen;
  private String phosphorus;
  private String potassium;
  private String ab;

}