package com.holitor.holitorservice.module.shop.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.shop.model.dto.ItemDto;
import com.holitor.holitorservice.module.shop.service.ItemService;

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
public class ItemController {
  
  private @Autowired ItemService itemService;

  @PostMapping("/item")
  @ResponseStatus(HttpStatus.CREATED)
  public ItemDto addItem(@RequestBody @Valid ItemDto itemDto) { return this.itemService.addItem(itemDto); }

  @GetMapping("/items")
  @ResponseStatus(HttpStatus.OK)
  public List<ItemDto> getItems() { return this.itemService.getItems(); }

  @PutMapping("/item")
  @ResponseStatus(HttpStatus.OK)
  public ItemDto updateItem(@RequestBody @Valid ItemDto itemDto) {
    try { itemDto = this.itemService.updateItem(itemDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return itemDto;
  }

  @DeleteMapping("/item/{idItem}")
  @ResponseStatus(HttpStatus.OK)
  public List<ItemDto> deleteItem(@PathVariable long idItem) {
    List<ItemDto> items = null;
    try { items = this.itemService.deleteItem(idItem); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return items;
  }

}