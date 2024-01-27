package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.VegetableDto;
import com.holitor.holitorservice.module.farm.service.VegetableService;

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
public class VegetableController {
  
  private @Autowired VegetableService vegetableService;

  @PostMapping("/vegetable")
  @ResponseStatus(HttpStatus.CREATED)
  public VegetableDto addVegetable(@RequestBody @Valid VegetableDto vegetableDto) { return this.vegetableService.addVegetable(vegetableDto); }

  @GetMapping("/vegetables")
  @ResponseStatus(HttpStatus.OK)
  public List<VegetableDto> getVegetables() { return this.vegetableService.getVegetables(); }

  @PutMapping("/vegetable")
  @ResponseStatus(HttpStatus.OK)
  public VegetableDto updateVegetable(@RequestBody @Valid VegetableDto vegetableDto) {
    try { vegetableDto = this.vegetableService.updateVegetable(vegetableDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return vegetableDto;
  }

  @PutMapping("/update-vegetables")
  @ResponseStatus(HttpStatus.OK)
  public List<VegetableDto> updateVegetables(@RequestBody @Valid List<VegetableDto> vegetablesDto) {
    try { vegetablesDto = this.vegetableService.updateVegetables(vegetablesDto); }
    catch (ApiException exception) {
      throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception);
    }
    return vegetablesDto;
  }

  @DeleteMapping("/vegetable/{idVegetable}")
  @ResponseStatus(HttpStatus.OK)
  public List<VegetableDto> deleteVegetable(@PathVariable long idVegetable) {
    List<VegetableDto> vegetables = null;
    try { vegetables = this.vegetableService.deleteVegetable(idVegetable); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return vegetables;
  }

}