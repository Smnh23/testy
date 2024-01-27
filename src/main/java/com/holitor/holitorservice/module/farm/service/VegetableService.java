package com.holitor.holitorservice.module.farm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.VegetableMapper;
import com.holitor.holitorservice.module.farm.model.Vegetable;
import com.holitor.holitorservice.module.farm.model.dto.VegetableDto;
import com.holitor.holitorservice.module.farm.repository.VegetableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VegetableService {
  
  private static final String VEGETABLE_NOT_FOUND = "Vegetable %s not found !";

  private @Autowired VegetableRepository vegetableRepository;
  private @Autowired VegetableMapper vegetableMapper;
  
  @Transactional(readOnly = false)
  public VegetableDto addVegetable(VegetableDto vegetableDto) { 
    Vegetable vegetable = this.vegetableMapper.toEntity(vegetableDto);
    return this.vegetableMapper.toDto(this.vegetableRepository.save(vegetable)); 
  }
  
  @Transactional(readOnly = true)
  public List<VegetableDto> getVegetables() { 
    List<Vegetable> vegetables = (List<Vegetable>) this.vegetableRepository.findAll();
    return vegetables.stream().map(vegetable -> this.vegetableMapper.toDto(vegetable)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public VegetableDto updateVegetable(VegetableDto vegetableDto) { 
    Vegetable vegetable = this.vegetableMapper.toEntity(vegetableDto);
    if (!this.vegetableRepository.existsById(vegetable.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(VEGETABLE_NOT_FOUND, vegetable.getId()));
    return this.vegetableMapper.toDto(this.vegetableRepository.save(vegetable)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<VegetableDto> updateVegetables(List<VegetableDto> vegetablesDto) { 
    List<Vegetable> vegetables = new ArrayList<Vegetable>();
    for (VegetableDto vegetableDto : vegetablesDto) vegetables.add(this.vegetableMapper.toEntity(vegetableDto));
    this.vegetableRepository.saveAll(vegetables);
    return vegetablesDto;
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<VegetableDto> deleteVegetable(long idVegetable) { 
    if (!this.vegetableRepository.existsById(idVegetable)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(VEGETABLE_NOT_FOUND, idVegetable));
    this.vegetableRepository.deleteById(idVegetable);
    return this.getVegetables();
  }

}