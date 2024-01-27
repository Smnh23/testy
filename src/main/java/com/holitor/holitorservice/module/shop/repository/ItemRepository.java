package com.holitor.holitorservice.module.shop.repository;

import com.holitor.holitorservice.module.shop.model.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> { }