package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.model.BotanicalFamily;
import com.holitor.holitorservice.module.farm.model.dto.BotanicalFamilyDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BotanicalFamilyMapper {

  public BotanicalFamilyDto toDto(BotanicalFamily input);

  public BotanicalFamily toEntity(BotanicalFamilyDto input);

}