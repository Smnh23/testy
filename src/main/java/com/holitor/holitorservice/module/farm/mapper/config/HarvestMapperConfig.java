package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Harvest;
import com.holitor.holitorservice.module.farm.model.dto.HarvestDto;
import com.holitor.holitorservice.module.stock.mapper.config.StorageMapperConfig;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface HarvestMapperConfig extends StorageMapperConfig {
  
  @InheritConfiguration(name = "mapStorage")
  @Mapping(target = "idProduction", source = "production.id")
  public void mapHarvest(Harvest input, @MappingTarget HarvestDto output);

  @InheritConfiguration(name = "mapStorageDto")
  @Mapping(target = "production", source = "idProduction", qualifiedByName = "idToProduction")
  public void mapHarvestDto(HarvestDto input, @MappingTarget Harvest output);
  
}