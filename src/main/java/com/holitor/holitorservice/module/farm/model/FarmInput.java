package com.holitor.holitorservice.module.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`farm_input`")
@Inheritance(strategy = InheritanceType.JOINED)
public class FarmInput {
  
  public enum FarmInputType { FERTILIZER, PESTICIDE, TOOL }

  @GeneratedValue(generator = "farm-input-generator")
  @GenericGenerator(name = "farm-input-generator", strategy = "increment")
  private @Id long id;
  
  @Column(name = "`name`")
  private String name;

  @Column(name = "`brand`")
  private String brand;

  @Column(name = "`price`")
  private double price;

  @Enumerated(EnumType.STRING)
  @Column(name = "`type`")
  private FarmInputType type;
  
}