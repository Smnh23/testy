package com.holitor.holitorservice.module.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "`pesticide`")
@PrimaryKeyJoinColumn(name = "`farm_input_id`")
@EqualsAndHashCode(callSuper = false)
public class Pesticide extends FarmInput {

  @Column(name = "`ab`")
  private boolean ab;
  
}