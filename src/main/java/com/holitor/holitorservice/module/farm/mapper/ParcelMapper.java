package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.Parcel;
import com.holitor.holitorservice.module.farm.model.dto.ParcelDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = GardenMapper.class)
public interface ParcelMapper {

  public ParcelDto toDto(Parcel input);

  public Parcel toEntity(ParcelDto input);

}