package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.Fertilizer;
import com.holitor.holitorservice.module.farm.model.dto.FertilizerDto;

import org.mapstruct.Mapper;

@Mapper(uses = FarmInputMapper.class, componentModel = "spring")
public interface FertilizerMapper {

  public FertilizerDto toDto(Fertilizer input);

  public Fertilizer toEntity(FertilizerDto input);
  
}