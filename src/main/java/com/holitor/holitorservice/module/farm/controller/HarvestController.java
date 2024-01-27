package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.HarvestDto;
import com.holitor.holitorservice.module.farm.service.HarvestService;

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
public class HarvestController {
  
  private @Autowired HarvestService harvestService;

  @PostMapping("/harvest")
  @ResponseStatus(HttpStatus.CREATED)
  public HarvestDto addHarvest(@RequestBody @Valid HarvestDto harvestDto) { return this.harvestService.addHarvest(harvestDto); }

  @GetMapping("/harvests")
  @ResponseStatus(HttpStatus.OK)
  public List<HarvestDto> getHarvests() { return this.harvestService.getHarvests(); }

  @PutMapping("/harvest")
  @ResponseStatus(HttpStatus.OK)
  public HarvestDto updateHarvest(@RequestBody @Valid HarvestDto harvestDto) {
    try { harvestDto = this.harvestService.updateHarvest(harvestDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return harvestDto;
  }

  @PutMapping("/update-harvests")
  @ResponseStatus(HttpStatus.OK)
  public List<HarvestDto> updateHarvests(@RequestBody @Valid List<HarvestDto> harvestsDto) {
    try { harvestsDto = this.harvestService.updateHarvests(harvestsDto); }
    catch (ApiException exception) {
      throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception);
    }
    return harvestsDto;
  }

  @DeleteMapping("/harvest/{idHarvest}")
  @ResponseStatus(HttpStatus.OK)
  public List<HarvestDto> deleteHarvest(@PathVariable long idHarvest) {
    List<HarvestDto> harvests = null;
    try { harvests = this.harvestService.deleteHarvest(idHarvest); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return harvests;
  }

}