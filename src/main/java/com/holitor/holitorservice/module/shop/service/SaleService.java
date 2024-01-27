package com.holitor.holitorservice.module.shop.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.shop.mapper.SaleMapper;
import com.holitor.holitorservice.module.shop.model.Sale;
import com.holitor.holitorservice.module.shop.model.dto.SaleDto;
import com.holitor.holitorservice.module.shop.repository.SaleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaleService {
  
  private static final String SALE_NOT_FOUND = "Sale %s not found !";

  private @Autowired SaleRepository saleRepository;
  private @Autowired SaleMapper saleMapper;

  @Transactional(readOnly = false)
  public SaleDto addSale(SaleDto saleDto) { 
    Sale sale = this.saleMapper.toEntity(saleDto);
    return this.saleMapper.toDto(this.saleRepository.save(sale)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<SaleDto> addSales(List<SaleDto> salesDto) { 
    List<Sale> sales = salesDto.stream().map(saleDto -> this.saleMapper.toEntity(saleDto)).collect(Collectors.toList());
    sales = (List<Sale>) this.saleRepository.saveAll(sales);
    return sales.stream().map(sale -> this.saleMapper.toDto(sale)).collect(Collectors.toList());
  }
  
  @Transactional(readOnly = true)
  public List<SaleDto> getSales() { 
    List<Sale> sales = (List<Sale>) this.saleRepository.findAll();
    return sales.stream().map(sale -> this.saleMapper.toDto(sale)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public SaleDto updateSale(SaleDto saleDto) { 
    Sale sale = this.saleMapper.toEntity(saleDto);
    if (!this.saleRepository.existsById(sale.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(SALE_NOT_FOUND, sale.getId()));
    return this.saleMapper.toDto(this.saleRepository.save(sale)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<SaleDto> deleteSale(long idSale) { 
    if (!this.saleRepository.existsById(idSale)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(SALE_NOT_FOUND, idSale));
    this.saleRepository.deleteById(idSale);
    return this.getSales();
  }

}