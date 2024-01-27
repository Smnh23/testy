package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.Unit;
import com.holitor.holitorservice.module.farm.model.dto.UnitDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitMapper {

  public UnitDto toDto(Unit input);

  public Unit toEntity(UnitDto input);

}