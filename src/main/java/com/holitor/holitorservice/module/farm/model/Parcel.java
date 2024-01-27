package com.holitor.holitorservice.module.farm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`parcel`")
public class Parcel {

  @GeneratedValue(generator = "parcel-generator")
  @GenericGenerator(name = "parcel-generator", strategy = "increment")
  private @Id long id;

  @Column(name = "`name`")
  private String name;

  @Column(name = "`length`")
  private double length;

  @Column(name = "`width`")
  private double width;

  @Column(name = "`greenhouse`")
  private boolean greenhouse;
  
  @OneToMany(targetEntity = Garden.class, mappedBy = "parcel", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Garden> gardens = new ArrayList<Garden>();

}