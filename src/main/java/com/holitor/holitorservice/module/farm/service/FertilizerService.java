package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.FertilizerMapper;
import com.holitor.holitorservice.module.farm.model.Fertilizer;
import com.holitor.holitorservice.module.farm.model.dto.FertilizerDto;
import com.holitor.holitorservice.module.farm.repository.FertilizerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FertilizerService {
  
  private static final String FERTILIZER_NOT_FOUND = "Fertilizer %s not found !";

  private @Autowired FertilizerRepository fertilizerRepository;
  private @Autowired FertilizerMapper fertilizerMapper;
  
  @Transactional(readOnly = false)
  public FertilizerDto addFertilizer(FertilizerDto fertilizerDto) { 
    Fertilizer fertilizer = this.fertilizerMapper.toEntity(fertilizerDto);
    return this.fertilizerMapper.toDto(this.fertilizerRepository.save(fertilizer)); 
  }
  
  @Transactional(readOnly = true)
  public List<FertilizerDto> getFertilizers() { 
    List<Fertilizer> fertilizers = (List<Fertilizer>) this.fertilizerRepository.findAll();
    return fertilizers.stream().map(fertilizer -> this.fertilizerMapper.toDto(fertilizer)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public FertilizerDto updateFertilizer(FertilizerDto fertilizerDto) { 
    Fertilizer fertilizer = this.fertilizerMapper.toEntity(fertilizerDto);
    if (!this.fertilizerRepository.existsById(fertilizer.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(FERTILIZER_NOT_FOUND, fertilizer.getId()));
    return this.fertilizerMapper.toDto(this.fertilizerRepository.save(fertilizer)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<FertilizerDto> deleteFertilizer(long idFertilizer) { 
    if (!this.fertilizerRepository.existsById(idFertilizer)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(FERTILIZER_NOT_FOUND, idFertilizer));
    this.fertilizerRepository.deleteById(idFertilizer);
    return this.getFertilizers();
  }

}