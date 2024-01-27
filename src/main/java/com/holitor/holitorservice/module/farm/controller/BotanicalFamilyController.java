package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.BotanicalFamilyDto;
import com.holitor.holitorservice.module.farm.service.BotanicalFamilyService;

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
public class BotanicalFamilyController {
  
  private @Autowired BotanicalFamilyService botanicalFamilyService;

  @PostMapping("/botanical-family")
  @ResponseStatus(HttpStatus.CREATED)
  public BotanicalFamilyDto addBotanicalFamily(@RequestBody @Valid BotanicalFamilyDto botanicalFamilyDto) {
    return this.botanicalFamilyService.addBotanicalFamily(botanicalFamilyDto);
  }

  @GetMapping("/botanical-families")
  @ResponseStatus(HttpStatus.OK)
  public List<BotanicalFamilyDto> getBotanicalFamilies() { return this.botanicalFamilyService.getBotanicalFamilies(); }

  @PutMapping("/botanical-family")
  @ResponseStatus(HttpStatus.OK)
  public BotanicalFamilyDto updateBotanicalFamily(@RequestBody @Valid BotanicalFamilyDto botanicalFamilyDto) {
    try { botanicalFamilyDto = this.botanicalFamilyService.updateBotanicalFamily(botanicalFamilyDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return botanicalFamilyDto;
  }

  @DeleteMapping("/botanical-family/{idBotanicalFamily}")
  @ResponseStatus(HttpStatus.OK)
  public List<BotanicalFamilyDto> deleteBotanicalFamily(@PathVariable long idBotanicalFamily) {
    List<BotanicalFamilyDto> botanicalFamilys = null;
    try { botanicalFamilys = this.botanicalFamilyService.deleteBotanicalFamily(idBotanicalFamily); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return botanicalFamilys;
  }

}