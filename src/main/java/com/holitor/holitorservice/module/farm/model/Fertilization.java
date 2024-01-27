package com.holitor.holitorservice.module.farm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`fertilization`")
public class Fertilization {

  @GeneratedValue(generator = "fertilization-generator")
  @GenericGenerator(name = "fertilization-generator", strategy = "increment")
  private @Id long id;
  
  @ManyToOne(targetEntity = Fertilizer.class)
  @JoinColumn(name = "`fertilizer_id`")
  private Fertilizer fertilizer;

  @Column(name = "`predicted_quantity`")
  private double predictedQuantity;

  @Column(name = "`actual_quantity`")
  private double actualQuantity;
  
  @Column(name = "`predicted_date`")
  private Date predictedDate;
  
  @Column(name = "`actual_date`")
  private Date actualDate;

  @Column(name = "`duration`")
  private long duration;
  
}