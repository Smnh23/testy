package com.holitor.holitorservice.module.farm.repository;

import com.holitor.holitorservice.module.farm.model.Unit;

import org.springframework.data.repository.CrudRepository;

public interface UnitRepository extends CrudRepository<Unit, Long> {

  public Unit findByNameAndFactor(String name, double factor);

}