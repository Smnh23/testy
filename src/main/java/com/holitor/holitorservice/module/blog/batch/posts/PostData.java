package com.holitor.holitorservice.module.blog.batch.posts;

import lombok.Data;

@Data
public class PostData {
  
  private String id;
  private String category;
  private String categoryImage;
  private String categoryDescription;
  private String title;
  private String description;
  private String content;
  private String image;
  
}