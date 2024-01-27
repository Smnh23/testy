package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.BotanicalFamilyMapper;
import com.holitor.holitorservice.module.farm.model.BotanicalFamily;
import com.holitor.holitorservice.module.farm.model.dto.BotanicalFamilyDto;
import com.holitor.holitorservice.module.farm.repository.BotanicalFamilyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BotanicalFamilyService {
  
  private static final String BOTANICAL_FAMILY_NOT_FOUND = "Botanical Family %s not found !";

  private @Autowired BotanicalFamilyRepository botanicalFamilyRepository;
  private @Autowired BotanicalFamilyMapper botanicalFamilyMapper;
  
  @Transactional(readOnly = false)
  public BotanicalFamilyDto addBotanicalFamily(BotanicalFamilyDto botanicalFamilyDto) { 
    BotanicalFamily botanicalFamily = this.botanicalFamilyMapper.toEntity(botanicalFamilyDto);
    return this.botanicalFamilyMapper.toDto(this.botanicalFamilyRepository.save(botanicalFamily)); 
  }
  
  @Transactional(readOnly = true)
  public List<BotanicalFamilyDto> getBotanicalFamilies() { 
    List<BotanicalFamily> botanicalFamilies = (List<BotanicalFamily>) this.botanicalFamilyRepository.findAll();
    return botanicalFamilies.stream().map(botanicalFamily -> this.botanicalFamilyMapper.toDto(botanicalFamily)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public BotanicalFamilyDto updateBotanicalFamily(BotanicalFamilyDto botanicalFamilyDto) { 
    BotanicalFamily botanicalFamily = this.botanicalFamilyMapper.toEntity(botanicalFamilyDto);
    if (!this.botanicalFamilyRepository.existsById(botanicalFamily.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(BOTANICAL_FAMILY_NOT_FOUND, botanicalFamily.getId()));
    return this.botanicalFamilyMapper.toDto(this.botanicalFamilyRepository.save(botanicalFamily)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<BotanicalFamilyDto> deleteBotanicalFamily(long idBotanicalFamily) { 
    if (!this.botanicalFamilyRepository.existsById(idBotanicalFamily)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(BOTANICAL_FAMILY_NOT_FOUND, idBotanicalFamily));
    this.botanicalFamilyRepository.deleteById(idBotanicalFamily);
    return this.getBotanicalFamilies();
  }

}