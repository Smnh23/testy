package com.holitor.holitorservice.module.farm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class ProductionKey implements Serializable {
  
  @Column(name = "`serie_id`")
  private long serieId;

  @Column(name = "`variety_id`")
  private long varietyId;

}