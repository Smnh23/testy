package com.holitor.holitorservice.module.farm.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`seedling`")
public class Seedling {
  
  @GeneratedValue(generator = "seedling-generator")
  @GenericGenerator(name = "seedling-generator", strategy = "increment")
  private @Id long id;
  
  @Column(name = "`predicted_quantity`")
  private long predictedQuantity;

  @Column(name = "`actual_quantity`")
  private long actualQuantity;
  
  @Column(name = "`date`")
  private Date date;

  @Column(name = "`duration`")
  private long duration;

}