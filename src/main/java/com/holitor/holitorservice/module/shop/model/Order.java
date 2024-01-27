package com.holitor.holitorservice.module.shop.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.holitor.holitorservice.module.user.model.User;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`order`")
public class Order {
  
  public enum Status { CREATION, WAITING_VALIDATION, VALIDATED, PREPARATION, DELIVERED, SHIFTED, CANCELED }
  public enum Payment { CHECK, CASH, ONLINE, NONE }

  @GeneratedValue(generator = "order-generator")
  @GenericGenerator(name = "order-generator", strategy = "increment")
  private @Id long id;
  
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "`user_id`")
  private User user;
  
  @ManyToOne(targetEntity = SalePoint.class)
  @JoinColumn(name = "`sale_point_id`", nullable = true)
  private SalePoint salePoint;
  
  @OneToMany(targetEntity = Item.class, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Item> items = new ArrayList<Item>();
  
  @Column(name = "`date`")
  private Date date;

  @Column(name = "`delivery_date`")
  private Date deliveryDate;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "`status`")
  private Status status;
  
  @Enumerated(EnumType.STRING)
  @Column(name = "`payment`")
  private Payment payment;
  
  @Column(name = "`paid`")
  private boolean paid;

}