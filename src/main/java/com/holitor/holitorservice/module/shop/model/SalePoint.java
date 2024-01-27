package com.holitor.holitorservice.module.shop.model;

import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`sale_point`")
public class SalePoint {
  
  @GeneratedValue(generator = "sale-point-generator")
  @GenericGenerator(name = "sale-point-generator", strategy = "increment")
  private @Id long id;

  @Column(name = "`actived`")
  private boolean actived;

  @Column(name = "`place`")
  private String place;

  @Column(name = "`zip_code`")
  private String zipCode;

  @Column(name = "`city`")
  private String city;

  @Column(name = "`day`")
  private int day;

  @Column(name = "`start_time`")
  private LocalTime startTime;

  @Column(name = "`end_time`")
  private LocalTime endTime;

  @Column(name = "`latitude`")
  private double latitude;

  @Column(name = "`longitude`")
  private double longitude;
  
}