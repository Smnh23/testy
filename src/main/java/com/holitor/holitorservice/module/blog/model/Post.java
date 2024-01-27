package com.holitor.holitorservice.module.blog.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.holitor.holitorservice.module.image.model.Image;
import com.holitor.holitorservice.module.user.model.User;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "`post`")
public class Post {

  @GeneratedValue(generator = "post-generator")
  @GenericGenerator(name = "post-generator", strategy = "increment")
  private @Id long id;
  
  @ManyToOne(targetEntity = User.class)
  @JoinColumn(name = "`user_id`")
  private User author;
  
  @ManyToOne(targetEntity = Category.class)
  @JoinColumn(name = "`category_id`")
  private Category category;
  
  @Column(name = "`title`")
  private String title;
  
  @Column(name = "`description`")
  private String description;
  
  @Column(name = "`content`")
  private @Lob String content;
  
  @OneToOne(targetEntity = Image.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "`image_id`")
  private Image image;
  
  @Column(name = "`date`")
  private Date date;

}