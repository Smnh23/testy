package com.holitor.holitorservice.module.user.mapper;

import com.holitor.holitorservice.module.image.mapper.ImageMapper;
import com.holitor.holitorservice.module.user.model.User;
import com.holitor.holitorservice.module.user.model.dto.UserDto;

import org.mapstruct.Mapper;

@Mapper(uses = ImageMapper.class, componentModel = "spring")
public interface UserMapper {

  public UserDto toDto(User input);
  
  public User toEntity(UserDto input);
  
}