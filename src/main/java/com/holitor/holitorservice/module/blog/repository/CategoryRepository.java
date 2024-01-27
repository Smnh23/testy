package com.holitor.holitorservice.module.blog.repository;

import java.util.Optional;

import com.holitor.holitorservice.module.blog.model.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> { 

  public Optional<Category> findByName(String name);

}