package com.holitor.holitorservice.module.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`crop_coefficient`")
public class CropCoefficient {
  
  @GeneratedValue(generator = "crop-coefficient-generator")
  @GenericGenerator(name = "crop-coefficient-generator", strategy = "increment")
  private @Id long id;

  @Column(name = "`numPeriod`")
  private int numPeriod;

  @Column(name = "`startPeriod`")
  private String startPeriod;

  @Column(name = "`endPeriod`")
  private String endPeriod;

  @Column(name = "`value`")
  private double value;
  
}
