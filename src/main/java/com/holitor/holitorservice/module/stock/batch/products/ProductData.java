package com.holitor.holitorservice.module.stock.batch.products;

import lombok.Data;

@Data
public class ProductData {
  
  private String id;
  private String idVariety;
  private String description;
  private String price;
  private String unitFactor;
  private String unitName;
  private String image;

}