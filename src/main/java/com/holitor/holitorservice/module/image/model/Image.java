package com.holitor.holitorservice.module.image.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@Entity
@AllArgsConstructor
@Table(name = "`image`")
public class Image {

  private static final String EMPTY_NAME = "empty.notype";
  private static final String EMPTY_TYPE = "notype";

  @GeneratedValue(generator = "image-generator")
  @GenericGenerator(name = "image-generator", strategy = "increment")
  private @Id long id;
  
  @Column(name = "`name`")
  private String name;
  
  @Column(name = "`type`")
  private String type;

  @Column(name = "`pic`")
  private @Lob byte[] pic;

  public Image() {
    this.name = Image.EMPTY_NAME;
    this.type = Image.EMPTY_TYPE;
    this.pic = new byte[]{};
  }

  public Image(String name, String type, byte[] pic) {
    this.name = name;
    this.type = type;
    this.pic = pic;
  }

}