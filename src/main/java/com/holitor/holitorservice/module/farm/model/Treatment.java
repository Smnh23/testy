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
@Table(name = "`treatment`")
public class Treatment {

  @GeneratedValue(generator = "treatment-generator")
  @GenericGenerator(name = "treatment-generator", strategy = "increment")
  private @Id long id;
  
  @ManyToOne(targetEntity = Pesticide.class)
  @JoinColumn(name = "`pesticide_id`")
  private Pesticide pesticide;

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