package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.UnitDto;
import com.holitor.holitorservice.module.farm.service.UnitService;

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
public class UnitController {
  
  private @Autowired UnitService unitService;

  @PostMapping("/unit")
  @ResponseStatus(HttpStatus.CREATED)
  public UnitDto addUnit(@RequestBody @Valid UnitDto unitDto) { return this.unitService.addUnit(unitDto); }

  @GetMapping("/units")
  @ResponseStatus(HttpStatus.OK)
  public List<UnitDto> getUnits() { return this.unitService.getUnits(); }

  @PutMapping("/unit")
  @ResponseStatus(HttpStatus.OK)
  public UnitDto updateUnit(@RequestBody @Valid UnitDto unitDto) {
    try { unitDto = this.unitService.updateUnit(unitDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return unitDto;
  }

  @DeleteMapping("/unit/{idUnit}")
  @ResponseStatus(HttpStatus.OK)
  public List<UnitDto> deleteUnit(@PathVariable long idUnit) {
    List<UnitDto> units = null;
    try { units = this.unitService.deleteUnit(idUnit); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return units;
  }

}