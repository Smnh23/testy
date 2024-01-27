package com.holitor.holitorservice.module.shop.mapper;

import com.holitor.holitorservice.module.shop.mapper.config.SalePointMapperConfig;
import com.holitor.holitorservice.module.shop.model.SalePoint;
import com.holitor.holitorservice.module.shop.model.dto.SalePointDto;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;

@Mapper(config = SalePointMapperConfig.class, componentModel = "spring")
public abstract class SalePointMapper {

  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapSalePoint")
  public abstract SalePointDto toDto(SalePoint input);

  @InheritConfiguration(name = "mapSalePointDto")
  public abstract SalePoint toEntity(SalePointDto input);

}