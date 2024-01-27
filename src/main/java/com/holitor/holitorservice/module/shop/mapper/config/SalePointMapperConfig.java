package com.holitor.holitorservice.module.shop.mapper.config;

import com.holitor.holitorservice.module.shop.model.SalePoint;
import com.holitor.holitorservice.module.shop.model.dto.SalePointDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface SalePointMapperConfig {
  
  @Mappings({

    @Mapping(target = "startTime", expression = "java(input.getStartTime().toString())"),
    @Mapping(target = "endTime", expression = "java(input.getEndTime().toString())")

  }) public void mapSalePoint(SalePoint input, @MappingTarget SalePointDto output);

  @Mappings({

    @Mapping(target = "startTime", expression = "java(java.time.LocalTime.parse(input.getStartTime().toString()))"),
    @Mapping(target = "endTime", expression = "java(java.time.LocalTime.parse(input.getEndTime().toString()))")

  }) public void mapSalePointDto(SalePointDto input, @MappingTarget SalePoint output);

}