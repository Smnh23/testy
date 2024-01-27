package com.holitor.holitorservice.module.blog.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.holitor.holitorservice.module.image.model.Image;

import lombok.Data;

@Data
@Entity
@Table(name = "`category`")
public class Category {
  
  @GeneratedValue(generator = "category-generator")
  @GenericGenerator(name = "category-generator", strategy = "increment")
  private @Id long id;

  @Column(name = "`color`")
  private String color;
  
  @Column(name = "`name`")
  private String name;
  
  @Column(name = "`description`")
  private @Lob String description;
  
  @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "`image_id`")
  private Image image;

}