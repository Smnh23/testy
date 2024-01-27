package com.holitor.holitorservice.module.shop.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.shop.model.dto.OrderDto;
import com.holitor.holitorservice.module.shop.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("${url.api}")
public class OrderController {

  private @Autowired OrderService orderService;

  @PostMapping("/order")
  @ResponseStatus(HttpStatus.CREATED)
  public OrderDto addOrder(@RequestBody @Valid OrderDto orderDto) { return this.orderService.addOrder(orderDto); }

  @GetMapping("/orders")
  @ResponseStatus(HttpStatus.OK)
  public List<OrderDto> getOrders() { return this.orderService.getOrders(); }

  @GetMapping("/orders/user/{idUser}")
  @ResponseStatus(HttpStatus.OK)
  public List<OrderDto> getOrdersByIdUser(@PathVariable long idUser) { return this.orderService.getOrdersByIdUser(idUser); }

  @PutMapping("/order")
  @ResponseStatus(HttpStatus.OK)
  public OrderDto updateOrder(@RequestBody @Valid OrderDto orderDto) {
    try { orderDto = this.orderService.updateOrder(orderDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return orderDto;
  }

  @PutMapping("/orders")
  @ResponseStatus(HttpStatus.OK)
  public List<OrderDto> updateOrders(@RequestBody @Valid List<OrderDto> ordersDto) {
    try { ordersDto = this.orderService.updateOrders(ordersDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return ordersDto;
  }

  @DeleteMapping("/order/{idOrder}")
  @ResponseStatus(HttpStatus.OK)
  public List<OrderDto> deleteOrder(@PathVariable long idOrder) {
    List<OrderDto> orders = null;
    try { orders = this.orderService.deleteOrder(idOrder); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return orders;
  }

}