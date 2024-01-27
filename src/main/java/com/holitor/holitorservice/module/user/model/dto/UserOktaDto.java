package com.holitor.holitorservice.module.user.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserOktaDto {
  
  private @NotNull String idOkta;
  private @Size(min=1, max=25) String pseudo;
  private @Email String email;

}
