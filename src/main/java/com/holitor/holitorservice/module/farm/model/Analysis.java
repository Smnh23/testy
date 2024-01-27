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
@Table(name = "`analysis`")
public class Analysis {

  @GeneratedValue(generator = "analysis-generator")
  @GenericGenerator(name = "analysis-generator", strategy = "increment")
  private @Id long id;

  @ManyToOne(targetEntity = Garden.class)
  @JoinColumn(name = "`garden_id`")
  private Garden garden; 
  
  @Column(name = "`date`")
  private Date date;

  @Column(name = "`clay`")
  private double clay;

  @Column(name = "`silt`")
  private double silt;

  @Column(name = "`sand`")
  private double sand;

  @Column(name = "`ph`")
  private double ph;
  
  @Column(name = "`nitrogen`")
  private double nitrogen;
  
  @Column(name = "`mo`")
  private double mo;

  @Column(name = "`cec`")
  private double cec;

  @Column(name = "`saturation_rate_cec`")
  private double saturationRateCEC;

  @Column(name = "`phosphorus`")
  private double phosphorus;

  @Column(name = "`potassium`")
  private double potassium;

  @Column(name = "`calcium`")
  private double calcium;

  @Column(name = "`magnesium`")
  private double magnesium;

  @Column(name = "`sodium`")
  private double sodium;

  @Column(name = "`boron`")
  private double boron;

  @Column(name = "`copper`")
  private double copper;

  @Column(name = "`zinc`")
  private double zinc;

  @Column(name = "`manganese`")
  private double manganese;

  @Column(name = "`iron`")
  private double iron;

}