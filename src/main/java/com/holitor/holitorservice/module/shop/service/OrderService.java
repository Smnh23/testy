package com.holitor.holitorservice.module.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.shop.model.dto.OrderDto;
import com.holitor.holitorservice.module.shop.mapper.OrderMapper;
import com.holitor.holitorservice.module.shop.model.Order;
import com.holitor.holitorservice.module.shop.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  private @Autowired OrderRepository orderRepository;
  private @Autowired OrderMapper orderMapper;

  @Transactional(readOnly = false)
  public OrderDto addOrder(OrderDto orderDto) {
    Order order = this.orderMapper.toEntity(orderDto); 
    return this.orderMapper.toDto(this.orderRepository.save(order)); 
  }

  @Transactional(readOnly = true)
  public List<OrderDto> getOrders() { 
    List<Order> orders = (List<Order>) this.orderRepository.findAll();
    return orders.stream().map(order -> this.orderMapper.toDto(order)).collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<OrderDto> getOrdersByIdUser(long idUser) {
    List<Order> orders = this.orderRepository.findByUserId(idUser);
    return orders.stream().map(order -> this.orderMapper.toDto(order)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public OrderDto updateOrder(OrderDto orderDto) { 
    Order order = this.orderMapper.toEntity(orderDto);
    if (!this.orderRepository.existsById(order.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, "Order "+order.getId()+" not found !");
    return this.orderMapper.toDto(this.orderRepository.save(order)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<OrderDto> updateOrders(List<OrderDto> ordersDto) { 
    List<Order> orders = new ArrayList<Order>();
    for (OrderDto orderDto : ordersDto) orders.add(this.orderMapper.toEntity(orderDto));
    this.orderRepository.saveAll(orders);
    return ordersDto;
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<OrderDto> deleteOrder(long idOrder) { 
    if (!this.orderRepository.existsById(idOrder)) 
      throw new ApiException(HttpStatus.NOT_FOUND, "Order "+idOrder+" not found !");
    this.orderRepository.deleteById(idOrder);
    return this.getOrders();
  }

}