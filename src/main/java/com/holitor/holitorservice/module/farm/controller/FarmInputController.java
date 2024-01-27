package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.FarmInputDto;
import com.holitor.holitorservice.module.farm.service.FarmInputService;

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
public class FarmInputController {
  
  private @Autowired FarmInputService farmInputService;

  @PostMapping("/farm-input")
  @ResponseStatus(HttpStatus.CREATED)
  public FarmInputDto addFarmInput(@RequestBody @Valid FarmInputDto farmInputDto) { return this.farmInputService.addFarmInput(farmInputDto); }

  @GetMapping("/farm-inputs")
  @ResponseStatus(HttpStatus.OK)
  public List<FarmInputDto> getFarmInputs() { return this.farmInputService.getFarmInputs(); }

  @PutMapping("/farm-input")
  @ResponseStatus(HttpStatus.OK)
  public FarmInputDto updateFarmInput(@RequestBody @Valid FarmInputDto farmInputDto) {
    try { farmInputDto = this.farmInputService.updateFarmInput(farmInputDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return farmInputDto;
  }

  @DeleteMapping("/farm-input/{idFarmInput}")
  @ResponseStatus(HttpStatus.OK)
  public List<FarmInputDto> deleteFarmInput(@PathVariable long idFarmInput) {
    List<FarmInputDto> farmInputs = null;
    try { farmInputs = this.farmInputService.deleteFarmInput(idFarmInput); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return farmInputs;
  }

}