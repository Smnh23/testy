package com.holitor.holitorservice.module.shop.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.shop.model.dto.SalePointDto;
import com.holitor.holitorservice.module.shop.service.SalePointService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("${url.api}")
public class SalePointController {
  
  private @Autowired SalePointService salePointService;

  @PostMapping("/sale-point")
  @ResponseStatus(HttpStatus.CREATED)
  public SalePointDto addSalePoint(@RequestBody @Valid SalePointDto salePointDto) { return this.salePointService.addSalePoint(salePointDto); }

  @GetMapping("/sale-points")
  @ResponseStatus(HttpStatus.OK)
  public List<SalePointDto> getSalePoints() { return this.salePointService.getSalePoints(); }

  @PutMapping("/sale-point")
  @ResponseStatus(HttpStatus.OK)
  public SalePointDto updateSalePoint(@RequestBody @Valid SalePointDto salePointDto) {
    try { salePointDto = this.salePointService.updateSalePoint(salePointDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return salePointDto;
  }

  @DeleteMapping("/sale-point/{idSalePoint}")
  @ResponseStatus(HttpStatus.OK)
  public List<SalePointDto> deleteSalePoint(@PathVariable long idSalePoint) {
    List<SalePointDto> salePoints = null;
    try { salePoints = this.salePointService.deleteSalePoint(idSalePoint); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return salePoints;
  }

}