package com.holitor.holitorservice.module.user.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.holitor.holitorservice.module.image.model.Image;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`user`")
public class User {

  @GeneratedValue(generator = "user-generator")
  @GenericGenerator(name = "user-generator", strategy = "increment")
  private @Id long id;
  
  @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "`image_id`")
  private Image image;
  
  @Column(name = "`pseudo`")
  private String pseudo;
  
  @Column(name = "`password`")
  private String password;
  
  @Column(name = "`email`")
  private String email;

}