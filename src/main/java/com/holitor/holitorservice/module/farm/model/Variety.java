package com.holitor.holitorservice.module.farm.model;

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
@Table(name = "`variety`")
public class Variety {
  
  @GeneratedValue(generator = "variety-generator")
  @GenericGenerator(name = "variety-generator", strategy = "increment")
  private @Id long id;

  @ManyToOne(targetEntity = Vegetable.class)
  @JoinColumn(name = "`vegetable_id`")
  private Vegetable vegetable;

  @Column(name = "`name`")
  private String name;

  @Column(name = "`hybridF1`")
  private boolean hybridF1;
  
  @Column(name = "`price`")
  private double price;

  @Column(name = "`planning`")
  private String planning;

}