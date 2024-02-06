package com.holitor.holitorservice.module.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.user.model.User;
import com.holitor.holitorservice.module.user.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  
  private @Autowired UserRepository userRepository;

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public UserDetails loadUserByUsername(String name) {
    User user = userRepository.findByPseudo(name).get();
    if (user == null) throw new ApiException(HttpStatus.NOT_FOUND, "User "+name+" not found !");
    return org.springframework.security.core.userdetails.User.builder()
      .username(user.getPseudo())
      .password(user.getPassword())
      .roles("CLIENT")
      .build();
  }

}
