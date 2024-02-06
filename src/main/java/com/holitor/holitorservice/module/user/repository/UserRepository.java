package com.holitor.holitorservice.module.user.repository;

import com.holitor.holitorservice.module.user.model.User;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  public Optional<User> findByPseudo(String pseudo);

  public Optional<User> findByPseudoAndPassword(String pseudo, String password);

}