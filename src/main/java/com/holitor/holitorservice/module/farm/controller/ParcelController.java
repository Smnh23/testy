package com.holitor.holitorservice.module.farm.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.model.dto.ParcelDto;
import com.holitor.holitorservice.module.farm.service.ParcelService;

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
public class ParcelController {
  
  private @Autowired ParcelService parcelService;

  @PostMapping("/parcel")
  @ResponseStatus(HttpStatus.CREATED)
  public ParcelDto addParcel(@RequestBody @Valid ParcelDto parcelDto) { return this.parcelService.addParcel(parcelDto); }

  @GetMapping("/parcels")
  @ResponseStatus(HttpStatus.OK)
  public List<ParcelDto> getParcels() { return this.parcelService.getParcels(); }

  @PutMapping("/parcel")
  @ResponseStatus(HttpStatus.OK)
  public ParcelDto updateParcel(@RequestBody @Valid ParcelDto parcelDto) {
    try { parcelDto = this.parcelService.updateParcel(parcelDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return parcelDto;
  }

  @DeleteMapping("/parcel/{idParcel}")
  @ResponseStatus(HttpStatus.OK)
  public List<ParcelDto> deleteParcel(@PathVariable long idParcel) {
    List<ParcelDto> parcels = null;
    try { parcels = this.parcelService.deleteParcel(idParcel); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return parcels;
  }

}