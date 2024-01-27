package com.holitor.holitorservice.module.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`botanical_family`")
public class BotanicalFamily {
  
  @GeneratedValue(generator = "botanical-family-generator")
  @GenericGenerator(name = "botanical-family-generator", strategy = "increment")
  private @Id long id;

  @Column(name = "`color`")
  private String color;

  @Column(name = "`name`")
  private String name;

  @Column(name = "`description`")
  private  @Lob String description;
  
}