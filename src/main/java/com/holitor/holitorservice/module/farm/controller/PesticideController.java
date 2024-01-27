package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.PesticideDto;
import com.holitor.holitorservice.module.farm.service.PesticideService;

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
public class PesticideController {
  
  private @Autowired PesticideService pesticideService;

  @PostMapping("/pesticide")
  @ResponseStatus(HttpStatus.CREATED)
  public PesticideDto addPesticide(@RequestBody @Valid PesticideDto pesticideDto) { return this.pesticideService.addPesticide(pesticideDto); }

  @GetMapping("/pesticides")
  @ResponseStatus(HttpStatus.OK)
  public List<PesticideDto> getPesticides() { return this.pesticideService.getPesticides(); }

  @PutMapping("/pesticide")
  @ResponseStatus(HttpStatus.OK)
  public PesticideDto updatePesticide(@RequestBody @Valid PesticideDto pesticideDto) {
    try { pesticideDto = this.pesticideService.updatePesticide(pesticideDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return pesticideDto;
  }

  @DeleteMapping("/pesticide/{idPesticide}")
  @ResponseStatus(HttpStatus.OK)
  public List<PesticideDto> deletePesticide(@PathVariable long idPesticide) {
    List<PesticideDto> pesticides = null;
    try { pesticides = this.pesticideService.deletePesticide(idPesticide); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return pesticides;
  }

}