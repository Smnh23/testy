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
@Table(name = "`board`")
public class Board {

  @GeneratedValue(generator = "board-generator")
  @GenericGenerator(name = "board-generator", strategy = "increment")
  private @Id long id;

  @Column(name = "`name`")
  private String name;

  @Column(name = "`length`")
  private double length;

  @Column(name = "`width`")
  private double width;

  @ManyToOne(targetEntity = Garden.class)
  @JoinColumn(name = "`garden_id`")
  private Garden garden; 

}