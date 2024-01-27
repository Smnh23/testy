package com.holitor.holitorservice.module.shop.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.holitor.holitorservice.module.stock.model.Storage;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "`sale`")
@PrimaryKeyJoinColumn(name = "`storage_id`")
@EqualsAndHashCode(callSuper = false)
public class Sale extends Storage {

  @ManyToOne(targetEntity = Order.class)
  @JoinColumn(name = "`order_id`")
  private Order order;
  
}