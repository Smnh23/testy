package com.holitor.holitorservice.module.user.repository;

import java.util.Optional;

import com.holitor.holitorservice.module.user.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> { 

  public boolean existsByIdOkta(String idOkta);

  public Optional<User> findByIdOkta(String idOkta);

}