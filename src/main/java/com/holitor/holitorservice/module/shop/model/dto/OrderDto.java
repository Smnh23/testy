package com.holitor.holitorservice.module.shop.model.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.holitor.holitorservice.module.shop.model.Order.Payment;
import com.holitor.holitorservice.module.shop.model.Order.Status;

import lombok.Data;

@Data
public class OrderDto {

  private @NotNull long id;
  private @NotNull long idUser;
  private @NotNull long idSalePoint;
  private List<ItemDto> items;
  private Date date;
  private Date deliveryDate;
  private Status status;
  private Payment payment;
  private boolean paid;

}