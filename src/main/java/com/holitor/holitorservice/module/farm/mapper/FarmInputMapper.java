package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.FarmInput;
import com.holitor.holitorservice.module.farm.model.dto.FarmInputDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FarmInputMapper {

  public FarmInputDto toDto(FarmInput input);

  public FarmInput toEntity(FarmInputDto input);

}