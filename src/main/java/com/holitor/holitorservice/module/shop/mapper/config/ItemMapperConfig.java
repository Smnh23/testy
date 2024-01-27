package com.holitor.holitorservice.module.shop.mapper.config;

import com.holitor.holitorservice.module.shop.model.Item;
import com.holitor.holitorservice.module.shop.model.dto.ItemDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface ItemMapperConfig {
  
  @Mappings({

    @Mapping(target = "idOrder", source = "order.id"),
    @Mapping(target = "idProduct", source = "product.id")

  }) public void mapItem(Item input, @MappingTarget ItemDto output);

  @Mappings({

    @Mapping(target = "order", source = "idOrder", qualifiedByName = "idToOrder"),
    @Mapping(target = "product", source = "idProduct", qualifiedByName = "idToProduct")

  }) public void mapItemDto(ItemDto input, @MappingTarget Item output);

}