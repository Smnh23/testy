package com.holitor.holitorservice.module.shop.mapper;

import com.holitor.holitorservice.module.shop.model.dto.ItemDto;
import com.holitor.holitorservice.module.shop.repository.OrderRepository;
import com.holitor.holitorservice.module.stock.model.Product;
import com.holitor.holitorservice.module.stock.repository.ProductRepository;
import com.holitor.holitorservice.module.shop.mapper.config.ItemMapperConfig;
import com.holitor.holitorservice.module.shop.model.Item;
import com.holitor.holitorservice.module.shop.model.Order;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = ItemMapperConfig.class, componentModel = "spring")
public abstract class ItemMapper {

  @Autowired OrderRepository orderRepository;
  @Autowired ProductRepository productRepository;

  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapItem")
  public abstract ItemDto toDto(Item input);

  @InheritConfiguration(name = "mapItemDto")
  public abstract Item toEntity(ItemDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToOrder")
  public final Order idToOrder(long id) { return orderRepository.findById(id).get(); }

  @Named("idToProduct")
  public final Product idToProduct(long id) { return productRepository.findById(id).get(); }
  
}