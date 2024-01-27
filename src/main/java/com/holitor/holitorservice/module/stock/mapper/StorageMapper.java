package com.holitor.holitorservice.module.stock.mapper;

import com.holitor.holitorservice.module.stock.mapper.config.StorageMapperConfig;
import com.holitor.holitorservice.module.stock.model.Product;
import com.holitor.holitorservice.module.stock.model.Storage;
import com.holitor.holitorservice.module.stock.model.dto.StorageDto;
import com.holitor.holitorservice.module.stock.repository.ProductRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = StorageMapperConfig.class, componentModel = "spring")
public abstract class StorageMapper {
  
  @Autowired ProductRepository productRepository;

  /* ------------------------- Abstract methods ------------------------- */
  
  @InheritConfiguration(name = "mapStorage")
  public abstract StorageDto toDto(Storage input);

  @InheritConfiguration(name = "mapStorageDto")
  public abstract Storage toEntity(StorageDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToProduct")
  public final Product idToProduct(long id) { return productRepository.findById(id).get(); }

}