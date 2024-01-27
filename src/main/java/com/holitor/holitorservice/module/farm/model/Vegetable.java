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
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`vegetable`")
public class Vegetable {

  public enum Irrigation { DROP_BY_DROP, SPRINKLE, BOTH, NONE }

  @GeneratedValue(generator = "vegetable-generator")
  @GenericGenerator(name = "vegetable-generator", strategy = "increment")
  private @Id long id;
  
  @ManyToOne(targetEntity = BotanicalFamily.class)
  @JoinColumn(name = "`botanical_family_id`")
  private BotanicalFamily botanicalFamily;

  @Column(name = "`name`")
  private String name;

  @Column(name = "`taxonomy`")
  private String taxonomy;

  @Column(name = "`color`")
  private String color;

  @Column(name = "`spacing_plant`")
  private double spacingPlant;

  @Column(name = "`spacing_rank`")
  private double spacingRank;

  @Column(name = "`yield`")
  private double yield;

  @Column(name = "`export_n`")
  private double exportN;

  @Column(name = "`export_p`")
  private double exportP;

  @Column(name = "`export_k`")
  private double exportK;

  @Column(name = "`ferti_n`")
  private double fertiN;

  @Column(name = "`ferti_p`")
  private double fertiP;

  @Column(name = "`ferti_k`")
  private double fertiK;
  
  @Column(name = "`price`")
  private double price;
  
  @Column(name = "`piece`")
  private boolean piece;

  @Column(name = "`planning`")
  private String planning;

  @Enumerated(EnumType.STRING)
  @Column(name = "`irrigation`")
  private Irrigation irrigation;

  @OneToMany(targetEntity = CropCoefficient.class, cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "`vegetable_id`")
  private List<CropCoefficient> kcs = new ArrayList<CropCoefficient>();
  
  @OneToMany(targetEntity = Variety.class, mappedBy = "vegetable", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Variety> varieties = new ArrayList<Variety>();

}