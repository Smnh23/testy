package com.holitor.holitorservice.module.shop.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.holitor.holitorservice.module.stock.model.Product;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`item`")
public class Item {
  
  @GeneratedValue(generator = "item-generator")
  @GenericGenerator(name = "item-generator", strategy = "increment")
  private @Id long id;
  
  @ManyToOne(targetEntity = Order.class)
  @JoinColumn(name = "`order_id`")
  private Order order; 
  
  @ManyToOne(targetEntity = Product.class, cascade = CascadeType.MERGE)
  @JoinColumn(name = "`product_id`")
  private Product product;
  
  @Column(name = "`quantity`")
  private int quantity;

  @Column(name = "`price`")
  private double price;

}