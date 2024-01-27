package com.holitor.holitorservice.module.farm.mapper.config;

import com.holitor.holitorservice.module.farm.model.Garden;
import com.holitor.holitorservice.module.farm.model.dto.GardenDto;

import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@MapperConfig()
public interface GardenMapperConfig {
 
  @Mapping(target = "idParcel", source = "parcel.id")
  public void mapGarden(Garden input, @MappingTarget GardenDto output);

  @Mapping(target = "parcel", source = "idParcel", qualifiedByName = "idToParcel")
  public void mapGardenDto(GardenDto input, @MappingTarget Garden output);

}