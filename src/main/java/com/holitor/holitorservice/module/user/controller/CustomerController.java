package com.holitor.holitorservice.module.user.controller;

import javax.validation.Valid;

import com.holitor.holitorservice.module.user.model.dto.UserOktaDto;
import com.holitor.holitorservice.module.user.model.dto.CustomerDto;
import com.holitor.holitorservice.module.user.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url.api}")
public class CustomerController {
  
  private @Autowired CustomerService customerService;

  @PostMapping("/customer")
  @ResponseStatus(HttpStatus.OK)
  public CustomerDto loadCustomer(@RequestBody @Valid UserOktaDto userOktaDto) { return this.customerService.loadCustomer(userOktaDto); }
  
}