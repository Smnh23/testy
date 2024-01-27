package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.SeedlingMapper;
import com.holitor.holitorservice.module.farm.model.Seedling;
import com.holitor.holitorservice.module.farm.model.dto.SeedlingDto;
import com.holitor.holitorservice.module.farm.repository.SeedlingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeedlingService {
  
  private static final String SEEDLING_NOT_FOUND = "Seedling %s not found !";

  private @Autowired SeedlingRepository seedlingRepository;
  private @Autowired SeedlingMapper seedlingMapper;
  
  @Transactional(readOnly = false)
  public SeedlingDto addSeedling(SeedlingDto seedlingDto) { 
    Seedling seedling = this.seedlingMapper.toEntity(seedlingDto);
    return this.seedlingMapper.toDto(this.seedlingRepository.save(seedling)); 
  }
  
  @Transactional(readOnly = true)
  public List<SeedlingDto> getSeedlings() { 
    List<Seedling> seedlings = (List<Seedling>) this.seedlingRepository.findAll();
    return seedlings.stream().map(seedling -> this.seedlingMapper.toDto(seedling)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public SeedlingDto updateSeedling(SeedlingDto seedlingDto) { 
    Seedling seedling = this.seedlingMapper.toEntity(seedlingDto);
    if (!this.seedlingRepository.existsById(seedling.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(SEEDLING_NOT_FOUND, seedling.getId()));
    return this.seedlingMapper.toDto(this.seedlingRepository.save(seedling)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<SeedlingDto> deleteSeedling(long idSeedling) { 
    if (!this.seedlingRepository.existsById(idSeedling)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(SEEDLING_NOT_FOUND, idSeedling));
    this.seedlingRepository.deleteById(idSeedling);
    return this.getSeedlings();
  }

}