package com.holitor.holitorservice.module.farm.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "`garden`")
public class Garden {

  @GeneratedValue(generator = "garden-generator")
  @GenericGenerator(name = "garden-generator", strategy = "increment")
  private @Id long id;

  @Column(name = "`name`")
  private String name;

  @Column(name = "`length`")
  private double length;

  @Column(name = "`width`")
  private double width;

  @Column(name = "`border`")
  private double border;

  @Column(name = "`foot_pass`")
  private double footPass;

  @OneToMany(targetEntity = Board.class, mappedBy = "garden", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Board> boards = new ArrayList<Board>();
  
  @OneToMany(targetEntity = Analysis.class, mappedBy = "garden", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Analysis> analyzes = new ArrayList<Analysis>();
  
  @ManyToOne(targetEntity = Parcel.class)
  @JoinColumn(name = "`parcel_id`")
  private Parcel parcel;

}