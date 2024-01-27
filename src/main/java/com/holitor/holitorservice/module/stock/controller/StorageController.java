package com.holitor.holitorservice.module.stock.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.stock.model.dto.StorageDto;
import com.holitor.holitorservice.module.stock.service.StorageService;

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
public class StorageController {
  
  private @Autowired StorageService storageService;

  @PostMapping("/storage")
  @ResponseStatus(HttpStatus.CREATED)
  public StorageDto addStorage(@RequestBody @Valid StorageDto storageDto) { return this.storageService.addStorage(storageDto); }

  @PostMapping("/add-storages")
  @ResponseStatus(HttpStatus.CREATED)
  public List<StorageDto> addStorages(@RequestBody @Valid List<StorageDto> storagesDto) { return this.storageService.addStorages(storagesDto); }

  @GetMapping("/storages")
  @ResponseStatus(HttpStatus.OK)
  public List<StorageDto> getStorages() { return this.storageService.getStorages(); }

  @PutMapping("/storage")
  @ResponseStatus(HttpStatus.OK)
  public StorageDto updateStorage(@RequestBody @Valid StorageDto storageDto) {
    try { storageDto = this.storageService.updateStorage(storageDto); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return storageDto;
  }

  @PutMapping("/update-storages")
  @ResponseStatus(HttpStatus.OK)
  public List<StorageDto> updateStorages(@RequestBody @Valid List<StorageDto> storagesDto) {
    try { storagesDto = this.storageService.updateStorages(storagesDto); }
    catch (ApiException exception) {
      throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception);
    }
    return storagesDto;
  }

  @DeleteMapping("/storage/{idStorage}")
  @ResponseStatus(HttpStatus.OK)
  public List<StorageDto> deleteStorage(@PathVariable long idStorage) {
    List<StorageDto> storages = null;
    try { storages = this.storageService.deleteStorage(idStorage); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return storages;
  }

}