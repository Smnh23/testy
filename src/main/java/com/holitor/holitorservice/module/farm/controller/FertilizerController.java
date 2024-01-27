package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.FertilizerDto;
import com.holitor.holitorservice.module.farm.service.FertilizerService;

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
public class FertilizerController {
  
  private @Autowired FertilizerService fertilizerService;

  @PostMapping("/fertilizer")
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto addFertilizer(@RequestBody @Valid FertilizerDto fertilizerDto) { return this.fertilizerService.addFertilizer(fertilizerDto); }

  @GetMapping("/fertilizers")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> getFertilizers() { return this.fertilizerService.getFertilizers(); }

  @PutMapping("/fertilizer")
  @ResponseStatus(HttpStatus.OK)
  public FertilizerDto updateFertilizer(@RequestBody @Valid FertilizerDto fertilizerDto) {
    try { fertilizerDto = this.fertilizerService.updateFertilizer(fertilizerDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return fertilizerDto;
  }

  @DeleteMapping("/fertilizer/{idFertilizer}")
  @ResponseStatus(HttpStatus.OK)
  public List<FertilizerDto> deleteFertilizer(@PathVariable long idFertilizer) {
    List<FertilizerDto> fertilizers = null;
    try { fertilizers = this.fertilizerService.deleteFertilizer(idFertilizer); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return fertilizers;
  }

}