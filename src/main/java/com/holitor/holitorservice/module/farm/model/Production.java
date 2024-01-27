package com.holitor.holitorservice.module.farm.model;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "`production`")
public class Production {

  @EmbeddedId
  private ProductionKey id;

  @ManyToOne
  @MapsId("serieId")
  @JoinColumn(name = "`serie_id`")
  private Serie serie;

  @ManyToOne
  @MapsId("varietyId")
  @JoinColumn(name = "`variety_id`")
  private Variety variety;

  @OneToOne(targetEntity = Seedling.class, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "`seedling_id`")
  private Seedling seedling;
  
}