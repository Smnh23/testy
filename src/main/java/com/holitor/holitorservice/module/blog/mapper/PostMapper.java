package com.holitor.holitorservice.module.blog.mapper;

import com.holitor.holitorservice.module.blog.mapper.config.PostMapperConfig;
import com.holitor.holitorservice.module.blog.model.Category;
import com.holitor.holitorservice.module.blog.model.Post;
import com.holitor.holitorservice.module.blog.model.dto.PostDto;
import com.holitor.holitorservice.module.blog.repository.CategoryRepository;
import com.holitor.holitorservice.module.image.mapper.ImageMapper;
import com.holitor.holitorservice.module.user.model.User;
import com.holitor.holitorservice.module.user.repository.UserRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = PostMapperConfig.class, uses = ImageMapper.class, componentModel = "spring")
public abstract class PostMapper {

  @Autowired CategoryRepository categoryRepository;
  @Autowired UserRepository userRepository;

  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapPost")
  public abstract PostDto toDto(Post input);
  
  @InheritConfiguration(name = "mapPostDto")
  public abstract Post toEntity(PostDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToCategory")
  public final Category idToCategory(long id) { return categoryRepository.findById(id).get(); }

  @Named("idToAuthor")
  public final User idToAuthor(long id) { return userRepository.findById(id).get(); }

}