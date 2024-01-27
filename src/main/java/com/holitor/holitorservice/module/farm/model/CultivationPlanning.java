package com.holitor.holitorservice.module.farm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`cultivation_planning`")
public class CultivationPlanning {
 
  @GeneratedValue(generator = "cultivation-planning-generator")
  @GenericGenerator(name = "cultivation-planning-generator", strategy = "increment")
  private @Id long id;
  
  @Column(name = "`begin`")
  private Date begin;

  @Column(name = "`harvest_begin`")
  private Date harvestBegin;

  @Column(name = "`harvest_end`")
  private Date harvestEnd;
  
}