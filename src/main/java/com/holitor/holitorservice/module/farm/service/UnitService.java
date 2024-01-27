package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.UnitMapper;
import com.holitor.holitorservice.module.farm.model.Unit;
import com.holitor.holitorservice.module.farm.model.dto.UnitDto;
import com.holitor.holitorservice.module.farm.repository.UnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnitService {
  
  private static final String UNIT_NOT_FOUND = "Unit %s not found !";

  private @Autowired UnitRepository unitRepository;
  private @Autowired UnitMapper unitMapper;
  
  @Transactional(readOnly = false)
  public UnitDto addUnit(UnitDto unitDto) { 
    Unit unit = this.unitMapper.toEntity(unitDto);
    return this.unitMapper.toDto(this.unitRepository.save(unit)); 
  }
  
  @Transactional(readOnly = true)
  public List<UnitDto> getUnits() { 
    List<Unit> units = (List<Unit>) this.unitRepository.findAll();
    return units.stream().map(unit -> this.unitMapper.toDto(unit)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public UnitDto updateUnit(UnitDto unitDto) { 
    Unit unit = this.unitMapper.toEntity(unitDto);
    if (!this.unitRepository.existsById(unit.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(UNIT_NOT_FOUND, unit.getId()));
    return this.unitMapper.toDto(this.unitRepository.save(unit)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<UnitDto> deleteUnit(long idUnit) { 
    if (!this.unitRepository.existsById(idUnit)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(UNIT_NOT_FOUND, idUnit));
    this.unitRepository.deleteById(idUnit);
    return this.getUnits();
  }

}