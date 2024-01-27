package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.Pesticide;
import com.holitor.holitorservice.module.farm.model.dto.PesticideDto;

import org.mapstruct.Mapper;

@Mapper(uses = FarmInputMapper.class, componentModel = "spring")
public interface PesticideMapper {

  public PesticideDto toDto(Pesticide input);

  public Pesticide toEntity(PesticideDto input);
  
}