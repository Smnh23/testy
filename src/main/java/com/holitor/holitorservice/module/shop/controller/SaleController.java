package com.holitor.holitorservice.module.shop.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.shop.model.dto.SaleDto;
import com.holitor.holitorservice.module.shop.service.SaleService;

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
public class SaleController {
  
  private @Autowired SaleService saleService;

  @PostMapping("/sale")
  @ResponseStatus(HttpStatus.CREATED)
  public SaleDto addSale(@RequestBody @Valid SaleDto saleDto) { return this.saleService.addSale(saleDto); }

  @PostMapping("/sales")
  @ResponseStatus(HttpStatus.CREATED)
  public List<SaleDto> addSales(@RequestBody @Valid List<SaleDto> salesDto) { return this.saleService.addSales(salesDto); }

  @GetMapping("/sales")
  @ResponseStatus(HttpStatus.OK)
  public List<SaleDto> getSales() { return this.saleService.getSales(); }

  @PutMapping("/sale")
  @ResponseStatus(HttpStatus.OK)
  public SaleDto updateSale(@RequestBody @Valid SaleDto saleDto) {
    try { saleDto = this.saleService.updateSale(saleDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return saleDto;
  }

  @DeleteMapping("/sale/{idSale}")
  @ResponseStatus(HttpStatus.OK)
  public List<SaleDto> deleteSale(@PathVariable long idSale) {
    List<SaleDto> sales = null;
    try { sales = this.saleService.deleteSale(idSale); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return sales;
  }

}