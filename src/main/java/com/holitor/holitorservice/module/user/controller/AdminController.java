package com.holitor.holitorservice.module.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

  @GetMapping("/isadmin")
  @PreAuthorize("hasAuthority('admin')")
  public Boolean isAdmin() { return true; }

}