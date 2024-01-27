package com.holitor.holitorservice.module.shop.mapper.config;

import com.holitor.holitorservice.module.shop.model.Order;
import com.holitor.holitorservice.module.shop.model.dto.OrderDto;

import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MapperConfig;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface OrderMapperConfig {
  
  @Mappings({

    @Mapping(target = "idUser", source = "user.id"),
    @Mapping(target = "idSalePoint", source = "salePoint.id")
  
  }) public void mapOrder(Order input, @MappingTarget OrderDto output);

  @Mappings({

    @Mapping(target = "user", source = "idUser", qualifiedByName = "idToUser"),
    @Mapping(target = "salePoint", source = "idSalePoint", qualifiedByName = "idToSalePoint")
  
  }) public void mapOrderDto(OrderDto input, @MappingTarget Order output);

}