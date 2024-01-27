package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.SerieMapper;
import com.holitor.holitorservice.module.farm.model.Serie;
import com.holitor.holitorservice.module.farm.model.dto.SerieDto;
import com.holitor.holitorservice.module.farm.repository.SerieRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SerieService {
  
  private static final String SERIE_NOT_FOUND = "Serie %s not found !";

  private @Autowired SerieRepository serieRepository;
  private @Autowired SerieMapper serieMapper;
  
  @Transactional(readOnly = false)
  public SerieDto addSerie(SerieDto serieDto) { 
    Serie serie = this.serieMapper.toEntity(serieDto);
    return this.serieMapper.toDto(this.serieRepository.save(serie)); 
  }
  
  @Transactional(readOnly = true)
  public List<SerieDto> getSeries() { 
    List<Serie> series = (List<Serie>) this.serieRepository.findAll();
    return series.stream().map(serie -> this.serieMapper.toDto(serie)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public SerieDto updateSerie(SerieDto serieDto) { 
    Serie serie = this.serieMapper.toEntity(serieDto);
    if (!this.serieRepository.existsById(serie.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(SERIE_NOT_FOUND, serie.getId()));
    return this.serieMapper.toDto(this.serieRepository.save(serie)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<SerieDto> deleteSerie(long idSerie) { 
    if (!this.serieRepository.existsById(idSerie)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(SERIE_NOT_FOUND, idSerie));
    this.serieRepository.deleteById(idSerie);
    return this.getSeries();
  }

}