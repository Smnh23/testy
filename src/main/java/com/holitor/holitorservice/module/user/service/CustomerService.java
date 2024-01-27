package com.holitor.holitorservice.module.user.service;

import com.holitor.holitorservice.module.user.model.dto.UserOktaDto;

import com.holitor.holitorservice.module.user.model.dto.CustomerDto;
import com.holitor.holitorservice.module.user.model.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

  private @Autowired UserService userService;
  
  @Transactional(readOnly = false)
  public CustomerDto loadCustomer(UserOktaDto userOktaDto) {
    CustomerDto customerDto = new CustomerDto();
    UserDto userDto = (this.userService.existsByIdOkta(userOktaDto))
      ? this.userService.getUserByIdOkta(userOktaDto.getIdOkta())
      : this.userService.addUser(userOktaDto);
    customerDto.setIdUser(userDto.getId());
    return customerDto;
  }

}