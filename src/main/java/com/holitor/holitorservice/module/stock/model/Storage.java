package com.holitor.holitorservice.module.stock.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`storage`")
@Inheritance(strategy = InheritanceType.JOINED)
public class Storage {

  public enum Action { HARVEST, SUPPLY, SHELVING, SALE, RESTOCK, DISCARD, CANCELED }
  
  @GeneratedValue(generator = "storage-generator")
  @GenericGenerator(name = "storage-generator", strategy = "increment")
  private @Id long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "`action`")
  private Action action;
  
  @Column(name = "`date`")
  private Date date;
  
  @Column(name = "`quantity`")
  private double quantity;

  @ManyToOne(targetEntity = Product.class)
  @JoinColumn(name = "`product_id`")
  private Product product;

  @Column(name = "`stock`")
  private double stock;

  @Column(name = "`shop`")
  private double shop;
  
}