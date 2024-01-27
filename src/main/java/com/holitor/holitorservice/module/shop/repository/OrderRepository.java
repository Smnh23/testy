package com.holitor.holitorservice.module.shop.repository;

import java.util.List;

import com.holitor.holitorservice.module.shop.model.Order;
import com.holitor.holitorservice.module.shop.model.Order.Status;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

  boolean existsByUserIdAndStatus(long idUser, Status status);

  List<Order> findByUserId(long idUser);
  
}