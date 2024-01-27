package com.holitor.holitorservice.module.farm.repository;

import com.holitor.holitorservice.module.farm.model.Production;
import com.holitor.holitorservice.module.farm.model.ProductionKey;

import org.springframework.data.repository.CrudRepository;

public interface ProductionRepository extends CrudRepository<Production, Long> {

  public Production findById(ProductionKey id);

}