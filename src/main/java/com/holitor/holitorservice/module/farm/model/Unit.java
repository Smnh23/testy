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
@Table(name = "`unit`")
public class Unit {
  
  @GeneratedValue(generator = "unit-generator")
  @GenericGenerator(name = "unit-generator", strategy = "increment")
  private @Id long id;
  
  @Column(name = "`name`")
  private String name;

  @Column(name = "`factor`")
  private double factor;

  @Column(name = "`piece`")
  private boolean piece;
  
}
