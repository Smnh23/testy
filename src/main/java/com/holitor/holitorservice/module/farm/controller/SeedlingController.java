package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.SeedlingDto;
import com.holitor.holitorservice.module.farm.service.SeedlingService;

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
public class SeedlingController {
  
  private @Autowired SeedlingService seedlingService;

  @PostMapping("/seedling")
  @ResponseStatus(HttpStatus.CREATED)
  public SeedlingDto addSeedling(@RequestBody @Valid SeedlingDto seedlingDto) { return this.seedlingService.addSeedling(seedlingDto); }
  
  @GetMapping("/seedlings")
  @ResponseStatus(HttpStatus.OK)
  public List<SeedlingDto> getSeedlings() { return this.seedlingService.getSeedlings(); }

  @PutMapping("/seedling")
  @ResponseStatus(HttpStatus.OK)
  public SeedlingDto updateSeedling(@RequestBody @Valid SeedlingDto seedlingDto) {
    try { seedlingDto = this.seedlingService.updateSeedling(seedlingDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return seedlingDto;
  }

  @DeleteMapping("/seedling/{idSeedling}")
  @ResponseStatus(HttpStatus.OK)
  public List<SeedlingDto> deleteSeedling(@PathVariable long idSeedling) {
    List<SeedlingDto> seedlings = null;
    try { seedlings = this.seedlingService.deleteSeedling(idSeedling); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return seedlings;
  }

}