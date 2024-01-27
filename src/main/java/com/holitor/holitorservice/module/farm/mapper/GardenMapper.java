package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.GardenMapperConfig;
import com.holitor.holitorservice.module.farm.model.Garden;
import com.holitor.holitorservice.module.farm.model.Parcel;
import com.holitor.holitorservice.module.farm.model.dto.GardenDto;
import com.holitor.holitorservice.module.farm.repository.ParcelRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = GardenMapperConfig.class, uses = { BoardMapper.class, AnalysisMapper.class }, componentModel = "spring")
public abstract class GardenMapper {

  @Autowired ParcelRepository parcelRepository;

  /* ------------------------- Abstract methods ------------------------- */
  
  @InheritConfiguration(name = "mapGarden")
  public abstract GardenDto toDto(Garden input);

  @InheritConfiguration(name = "mapGardenDto")
  public abstract Garden toEntity(GardenDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToParcel")
  public final Parcel idToParcel(long id) { return parcelRepository.findById(id).get(); }

}