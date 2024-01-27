package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.SerieDto;
import com.holitor.holitorservice.module.farm.service.SerieService;

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
public class SerieController {
  
  private @Autowired SerieService serieService;

  @PostMapping("/serie")
  @ResponseStatus(HttpStatus.CREATED)
  public SerieDto addSerie(@RequestBody @Valid SerieDto serieDto) { return this.serieService.addSerie(serieDto); }
  
  @GetMapping("/series")
  @ResponseStatus(HttpStatus.OK)
  public List<SerieDto> getSeries() { return this.serieService.getSeries(); }

  @PutMapping("/serie")
  @ResponseStatus(HttpStatus.OK)
  public SerieDto updateSerie(@RequestBody @Valid SerieDto serieDto) {
    try { serieDto = this.serieService.updateSerie(serieDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return serieDto;
  }

  @DeleteMapping("/serie/{idSerie}")
  @ResponseStatus(HttpStatus.OK)
  public List<SerieDto> deleteSerie(@PathVariable long idSerie) {
    List<SerieDto> series = null;
    try { series = this.serieService.deleteSerie(idSerie); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return series;
  }

}