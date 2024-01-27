package com.holitor.holitorservice.module.stock.mapper.config;

import com.holitor.holitorservice.module.stock.model.Storage;
import com.holitor.holitorservice.module.stock.model.dto.StorageDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface StorageMapperConfig {
  
  @Mapping(target = "idProduct", source = "product.id")
  public void mapStorage(Storage input, @MappingTarget StorageDto output);

  @Mapping(target = "product", source = "idProduct", qualifiedByName = "idToProduct")
  public void mapStorageDto(StorageDto input, @MappingTarget Storage output);

}