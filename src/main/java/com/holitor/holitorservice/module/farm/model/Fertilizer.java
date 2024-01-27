package com.holitor.holitorservice.module.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "`fertilizer`")
@PrimaryKeyJoinColumn(name = "`farm_input_id`")
@EqualsAndHashCode(callSuper = false)
public class Fertilizer extends FarmInput {

  @Column(name = "`nitrogen`")
  private double nitrogen;

  @Column(name = "`phosphorus`")
  private double phosphorus;

  @Column(name = "`potassium`")
  private double potassium;

  @Column(name = "`ab`")
  private boolean ab;
  
}