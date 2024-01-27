package com.holitor.holitorservice.module.farm.repository;

import com.holitor.holitorservice.module.farm.model.Vegetable;

import org.springframework.data.repository.CrudRepository;

public interface VegetableRepository extends CrudRepository<Vegetable, Long> { }