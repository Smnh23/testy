package com.holitor.holitorservice.module.user.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// import com.holitor.holitorservice.module.image.model.dto.ImageDto;

import lombok.Data;

@Data
public class UserDto {
  
  private @NotNull long id;
  // private ImageDto image;
  private @Size(min=1, max=25) String pseudo;
  private String password;
  private @Email String email;

}