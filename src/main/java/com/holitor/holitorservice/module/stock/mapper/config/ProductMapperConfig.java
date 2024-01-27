package com.holitor.holitorservice.module.stock.mapper.config;

import com.holitor.holitorservice.module.stock.model.Product;
import com.holitor.holitorservice.module.stock.model.dto.ProductDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface ProductMapperConfig {
  
  @Mappings({

    @Mapping(target = "idUnit", source = "unit.id"),
    @Mapping(target = "idVariety", source = "variety.id")

  }) public void mapProduct(Product input, @MappingTarget ProductDto output);

  @Mappings({

    @Mapping(target = "unit", source = "idUnit", qualifiedByName = "idToUnit"),
    @Mapping(target = "variety", source = "idVariety", qualifiedByName = "idToVariety")

  }) public void mapProductDto(ProductDto input, @MappingTarget Product output);

}