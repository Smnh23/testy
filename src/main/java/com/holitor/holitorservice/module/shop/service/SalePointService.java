package com.holitor.holitorservice.module.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.shop.mapper.SalePointMapper;
import com.holitor.holitorservice.module.shop.model.SalePoint;
import com.holitor.holitorservice.module.shop.model.dto.SalePointDto;
import com.holitor.holitorservice.module.shop.repository.SalePointRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SalePointService {
  
  private static final String SALE_POINT_NOT_FOUND = "Sale point %s not found !";

  private @Autowired SalePointRepository salePointRepository;
  private @Autowired SalePointMapper salePointMapper;

  @Transactional(readOnly = false)
  public SalePointDto addSalePoint(SalePointDto salePointDto) { 
    SalePoint salePoint = this.salePointMapper.toEntity(salePointDto);
    return this.salePointMapper.toDto(this.salePointRepository.save(salePoint)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<SalePointDto> addSalePoints(List<SalePointDto> salePointsDto) { 
    List<SalePoint> salePoints = salePointsDto.stream().map(salePointDto -> this.salePointMapper.toEntity(salePointDto)).collect(Collectors.toList());
    salePoints = (List<SalePoint>) this.salePointRepository.saveAll(salePoints);
    return salePoints.stream().map(salePoint -> this.salePointMapper.toDto(salePoint)).collect(Collectors.toList());
  }
  
  @Transactional(readOnly = true)
  public List<SalePointDto> getSalePoints() { 
    List<SalePoint> salePoints = (List<SalePoint>) this.salePointRepository.findAll();
    return salePoints.stream().map(salePoint -> this.salePointMapper.toDto(salePoint)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public SalePointDto updateSalePoint(SalePointDto salePointDto) { 
    SalePoint salePoint = this.salePointMapper.toEntity(salePointDto);
    if (!this.salePointRepository.existsById(salePoint.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(SALE_POINT_NOT_FOUND, salePoint.getId()));
    return this.salePointMapper.toDto(this.salePointRepository.save(salePoint)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<SalePointDto> deleteSalePoint(long idSalePoint) { 
    if (!this.salePointRepository.existsById(idSalePoint)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(SALE_POINT_NOT_FOUND, idSalePoint));
    this.salePointRepository.deleteById(idSalePoint);
    return this.getSalePoints();
  }

}