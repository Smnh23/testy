package com.holitor.holitorservice.module.stock.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.holitor.holitorservice.module.farm.model.Unit;
import com.holitor.holitorservice.module.farm.model.Variety;
import com.holitor.holitorservice.module.image.model.Image;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`product`")
public class Product {

  @GeneratedValue(generator = "product-generator")
  @GenericGenerator(name = "product-generator", strategy = "increment")
  private @Id long id;
  
  @Column(name = "`name`")
  private String name;

  @Column(name = "`description`")
  private @Lob String description;
  
  @Column(name = "`price`")
  private double price;
  
  @ManyToOne(targetEntity = Unit.class)
  @JoinColumn(name = "`unit_id`")
  private Unit unit;

  @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "`image_id`")
  private Image image;
  
  @ManyToOne(targetEntity = Variety.class)
  @JoinColumn(name = "`variety_id`", nullable = true)
  private Variety variety;

}