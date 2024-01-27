package com.holitor.holitorservice.module.farm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.holitor.holitorservice.module.stock.model.Storage;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "`harvest`")
@PrimaryKeyJoinColumn(name = "`storage_id`")
@EqualsAndHashCode(callSuper = false)
public class Harvest extends Storage {

  @ManyToOne(targetEntity = Production.class)
  @JoinColumns({ @JoinColumn(name = "`serie_id`"), @JoinColumn(name = "`variety_id`") })
  private Production production;

  @Column(name = "`duration`")
  private long duration;
  
}