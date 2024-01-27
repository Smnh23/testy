package com.holitor.holitorservice.module.shop.mapper.config;

import com.holitor.holitorservice.module.shop.model.Sale;
import com.holitor.holitorservice.module.shop.model.dto.SaleDto;
import com.holitor.holitorservice.module.stock.mapper.config.StorageMapperConfig;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface SaleMapperConfig extends StorageMapperConfig {
  
  @InheritConfiguration(name = "mapStorage")
  @Mapping(target = "idOrder", source = "order.id")
  public void mapSale(Sale input, @MappingTarget SaleDto output);

  @InheritConfiguration(name = "mapStorageDto")
  @Mapping(target = "order", source = "idOrder", qualifiedByName = "idToOrder")
  public void mapSaleDto(SaleDto input, @MappingTarget Sale output);

}