package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.PesticideMapper;
import com.holitor.holitorservice.module.farm.model.Pesticide;
import com.holitor.holitorservice.module.farm.model.dto.PesticideDto;
import com.holitor.holitorservice.module.farm.repository.PesticideRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PesticideService {
  
  private static final String PESTICIDE_NOT_FOUND = "Pesticide %s not found !";

  private @Autowired PesticideRepository pesticideRepository;
  private @Autowired PesticideMapper pesticideMapper;
  
  @Transactional(readOnly = false)
  public PesticideDto addPesticide(PesticideDto pesticideDto) { 
    Pesticide pesticide = this.pesticideMapper.toEntity(pesticideDto);
    return this.pesticideMapper.toDto(this.pesticideRepository.save(pesticide)); 
  }
  
  @Transactional(readOnly = true)
  public List<PesticideDto> getPesticides() { 
    List<Pesticide> pesticides = (List<Pesticide>) this.pesticideRepository.findAll();
    return pesticides.stream().map(pesticide -> this.pesticideMapper.toDto(pesticide)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public PesticideDto updatePesticide(PesticideDto pesticideDto) { 
    Pesticide pesticide = this.pesticideMapper.toEntity(pesticideDto);
    if (!this.pesticideRepository.existsById(pesticide.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(PESTICIDE_NOT_FOUND, pesticide.getId()));
    return this.pesticideMapper.toDto(this.pesticideRepository.save(pesticide)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<PesticideDto> deletePesticide(long idPesticide) { 
    if (!this.pesticideRepository.existsById(idPesticide)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(PESTICIDE_NOT_FOUND, idPesticide));
    this.pesticideRepository.deleteById(idPesticide);
    return this.getPesticides();
  }

}