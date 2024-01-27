package com.holitor.holitorservice.module.farm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.HarvestMapper;
import com.holitor.holitorservice.module.farm.model.Harvest;
import com.holitor.holitorservice.module.farm.model.dto.HarvestDto;
import com.holitor.holitorservice.module.farm.repository.HarvestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HarvestService {
  
  private static final String HARVEST_NOT_FOUND = "Harvest %s not found !";

  private @Autowired HarvestRepository harvestRepository;
  private @Autowired HarvestMapper harvestMapper;
  
  @Transactional(readOnly = false)
  public HarvestDto addHarvest(HarvestDto harvestDto) { 
    Harvest harvest = this.harvestMapper.toEntity(harvestDto);
    return this.harvestMapper.toDto(this.harvestRepository.save(harvest)); 
  }

  @Transactional(readOnly = true)
  public List<HarvestDto> getHarvests() { 
    List<Harvest> harvests = (List<Harvest>) this.harvestRepository.findAll();
    return harvests.stream().map(harvest -> this.harvestMapper.toDto(harvest)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public HarvestDto updateHarvest(HarvestDto harvestDto) { 
    Harvest harvest = this.harvestMapper.toEntity(harvestDto);
    if (!this.harvestRepository.existsById(harvest.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(HARVEST_NOT_FOUND, harvest.getId()));
    return this.harvestMapper.toDto(this.harvestRepository.save(harvest)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<HarvestDto> updateHarvests(List<HarvestDto> harvestsDto) { 
    List<Harvest> harvests = new ArrayList<Harvest>();
    for (HarvestDto harvestDto : harvestsDto) harvests.add(this.harvestMapper.toEntity(harvestDto));
    this.harvestRepository.saveAll(harvests);
    return harvestsDto;
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<HarvestDto> deleteHarvest(long idHarvest) { 
    if (!this.harvestRepository.existsById(idHarvest)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(HARVEST_NOT_FOUND, idHarvest));
    this.harvestRepository.deleteById(idHarvest);
    return this.getHarvests();
  }

}