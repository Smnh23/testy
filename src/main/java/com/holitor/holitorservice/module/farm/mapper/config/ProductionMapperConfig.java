package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Production;
import com.holitor.holitorservice.module.farm.model.dto.ProductionDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface ProductionMapperConfig {
  
  @Mappings({

    @Mapping(target = "idSerie", source = "serie.id"),
    @Mapping(target = "idVariety", source = "variety.id")

  }) public void mapProduction(Production input, @MappingTarget ProductionDto output);

  @Mappings({

    @Mapping(target = "serie", source = "idSerie", qualifiedByName = "idToSerie"),
    @Mapping(target = "variety", source = "idVariety", qualifiedByName = "idToVariety")

  }) public void mapProductionDto(ProductionDto input, @MappingTarget Production output);

}