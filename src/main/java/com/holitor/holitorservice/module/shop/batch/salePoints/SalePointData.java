package com.holitor.holitorservice.module.shop.batch.salePoints;

import lombok.Data;

@Data
public class SalePointData {
  
  private String id;
  private String place;
  private String zipCode;
  private String city;
  private String day;
  private String startTime;
  private String endTime;
  private String latitude;
  private String longitude;

}