package com.holitor.holitorservice.module.blog.mapper.config;

import com.holitor.holitorservice.module.blog.model.Post;
import com.holitor.holitorservice.module.blog.model.dto.PostDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface PostMapperConfig {
  
  @Mappings({

    @Mapping(target = "idCategory", source = "category.id"),
    @Mapping(target = "idAuthor", source = "author.id")
  
  }) public void mapPost(Post input, @MappingTarget PostDto output);

  @Mappings({

    @Mapping(target = "category", source = "idCategory", qualifiedByName = "idToCategory"),
    @Mapping(target = "author", source = "idAuthor", qualifiedByName = "idToAuthor")
  
  }) public void mapPostDto(PostDto input, @MappingTarget Post output);

}