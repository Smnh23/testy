package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.FarmInputMapper;
import com.holitor.holitorservice.module.farm.model.FarmInput;
import com.holitor.holitorservice.module.farm.model.dto.FarmInputDto;
import com.holitor.holitorservice.module.farm.repository.FarmInputRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FarmInputService {
  
  private static final String FARM_INPUT_NOT_FOUND = "Farm input %s not found !";

  private @Autowired FarmInputRepository farmInputRepository;
  private @Autowired FarmInputMapper farmInputMapper;
  
  @Transactional(readOnly = false)
  public FarmInputDto addFarmInput(FarmInputDto farmInputDto) { 
    FarmInput farmInput = this.farmInputMapper.toEntity(farmInputDto);
    return this.farmInputMapper.toDto(this.farmInputRepository.save(farmInput)); 
  }
  
  @Transactional(readOnly = true)
  public List<FarmInputDto> getFarmInputs() { 
    List<FarmInput> farmInputs = (List<FarmInput>) this.farmInputRepository.findAll();
    return farmInputs.stream().map(farmInput -> this.farmInputMapper.toDto(farmInput)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public FarmInputDto updateFarmInput(FarmInputDto farmInputDto) { 
    FarmInput farmInput = this.farmInputMapper.toEntity(farmInputDto);
    if (!this.farmInputRepository.existsById(farmInput.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(FARM_INPUT_NOT_FOUND, farmInput.getId()));
    return this.farmInputMapper.toDto(this.farmInputRepository.save(farmInput)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<FarmInputDto> deleteFarmInput(long idFarmInput) { 
    if (!this.farmInputRepository.existsById(idFarmInput)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(FARM_INPUT_NOT_FOUND, idFarmInput));
    this.farmInputRepository.deleteById(idFarmInput);
    return this.getFarmInputs();
  }

}