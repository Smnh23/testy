package com.holitor.holitorservice.module.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.shop.model.dto.ItemDto;
import com.holitor.holitorservice.module.shop.mapper.ItemMapper;
import com.holitor.holitorservice.module.shop.model.Item;
import com.holitor.holitorservice.module.shop.repository.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {

  private @Autowired ItemRepository itemRepository;
  private @Autowired ItemMapper itemMapper;

  @Transactional(readOnly = false)
  public ItemDto addItem(ItemDto itemDto) { 
    Item item = this.itemMapper.toEntity(itemDto);
    return this.itemMapper.toDto(this.itemRepository.save(item)); 
  }
  
  @Transactional(readOnly = true)
  public List<ItemDto> getItems() { 
    List<Item> items = (List<Item>) this.itemRepository.findAll();
    return items.stream().map(item -> this.itemMapper.toDto(item)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public ItemDto updateItem(ItemDto itemDto) { 
    Item item = this.itemMapper.toEntity(itemDto);
    if (!this.itemRepository.existsById(item.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, "Item "+item.getId()+" not found !");
    return this.itemMapper.toDto(this.itemRepository.save(item)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<ItemDto> deleteItem(long idItem) { 
    if (!this.itemRepository.existsById(idItem)) 
      throw new ApiException(HttpStatus.NOT_FOUND, "Item "+idItem+" not found !");
    this.itemRepository.deleteById(idItem);
    return this.getItems();
  }

}