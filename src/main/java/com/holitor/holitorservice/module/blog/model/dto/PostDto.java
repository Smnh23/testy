package com.holitor.holitorservice.module.blog.model.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.holitor.holitorservice.module.image.model.dto.ImageDto;

import lombok.Data;

@Data
public class PostDto {
  
  private @NotNull long id;
  private @NotNull long idAuthor;
  private @NotNull long idCategory;
  private @Size(min=1, max=25) String title;
  private @Size(min=1, max=500) String description;
  private @Size(min=1, max=10000) String content;
  private ImageDto image;
  private Date date;

}