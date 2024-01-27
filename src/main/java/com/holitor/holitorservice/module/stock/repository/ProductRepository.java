package com.holitor.holitorservice.module.stock.repository;

import com.holitor.holitorservice.module.stock.model.Product;

import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> { }