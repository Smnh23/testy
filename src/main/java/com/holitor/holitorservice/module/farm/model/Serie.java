package com.holitor.holitorservice.module.farm.model;

import java.util.ArrayList;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.holitor.holitorservice.module.farm.model.Vegetable.Irrigation;

import lombok.Data;

@Data
@Entity
@Table(name = "`serie`")
public class Serie {

  @GeneratedValue(generator = "serie-generator")
  @GenericGenerator(name = "serie-generator", strategy = "increment")
  private @Id long id;

  @ManyToOne(targetEntity = Board.class)
  @JoinColumn(name = "`board_id`")
  private Board board;

  @Column(name = "`spacing_plant`")
  private double spacingPlant;

  @Column(name = "`spacing_rank`")
  private double spacingRank;

  @Column(name = "`yield`")
  private double yield;

  @Column(name = "`price`")
  private double price;

  @Column(name = "`sown`")
  private boolean sown;
  
  @OneToOne(targetEntity = CultivationPlanning.class, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "`planning_id`")
  private CultivationPlanning planning;

  @Enumerated(EnumType.STRING)
  @Column(name = "`irrigation`")
  private Irrigation irrigation;
  
  @OneToMany(mappedBy = "serie", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Production> productions = new ArrayList<Production>();
  
  @OneToMany(targetEntity = Fertilization.class, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "`serie_id`")
  private List<Fertilization> fertilizations = new ArrayList<Fertilization>();
  
  @OneToMany(targetEntity = Treatment.class, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "`serie_id`")
  private List<Treatment> treatments = new ArrayList<Treatment>();
  
  @OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "`serie_id`")
  private List<Task> tasks = new ArrayList<Task>();

}