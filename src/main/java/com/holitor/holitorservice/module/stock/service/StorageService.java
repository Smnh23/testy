package com.holitor.holitorservice.module.stock.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.stock.mapper.StorageMapper;
import com.holitor.holitorservice.module.stock.repository.StorageRepository;
import com.holitor.holitorservice.module.stock.model.Storage;
import com.holitor.holitorservice.module.stock.model.dto.StorageDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StorageService {
  
  private static final String STOCK_HISTORY_NOT_FOUND = "Storage %s not found !";

  private @Autowired StorageRepository storageRepository;
  private @Autowired StorageMapper storageMapper;

  @Transactional(readOnly = false)
  public StorageDto addStorage(StorageDto storageDto) { 
    Storage storage = this.storageMapper.toEntity(storageDto);
    return this.storageMapper.toDto(this.storageRepository.save(storage)); 
  }

  @Transactional(readOnly = false)
  public List<StorageDto> addStorages(List<StorageDto> storagesDto) {
    List<Storage> storages = new ArrayList<Storage>();
    for (StorageDto storageDto : storagesDto) storages.add(this.storageMapper.toEntity(storageDto));
    List<Storage> addedStorages = (List<Storage>) this.storageRepository.saveAll(storages);
    List<StorageDto> addedStoragesDto = new ArrayList<StorageDto>();
    for (Storage addStorage : addedStorages) addedStoragesDto.add(this.storageMapper.toDto(addStorage));
    return addedStoragesDto;
  }

  @Transactional(readOnly = true)
  public List<StorageDto> getStorages() { 
    List<Storage> storages = (List<Storage>) this.storageRepository.findAll();
    return storages.stream().map(storage -> this.storageMapper.toDto(storage)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public StorageDto updateStorage(StorageDto storageDto) { 
    Storage storage = this.storageMapper.toEntity(storageDto);
    if (!this.storageRepository.existsById(storage.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(STOCK_HISTORY_NOT_FOUND, storage.getId()));
    return this.storageMapper.toDto(this.storageRepository.save(storage)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<StorageDto> updateStorages(List<StorageDto> storagesDto) { 
    List<Storage> storages = new ArrayList<Storage>();
    for (StorageDto storageDto : storagesDto) storages.add(this.storageMapper.toEntity(storageDto));
    this.storageRepository.saveAll(storages);
    return storagesDto;
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<StorageDto> deleteStorage(long idStorage) { 
    if (!this.storageRepository.existsById(idStorage)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(STOCK_HISTORY_NOT_FOUND, idStorage));
    this.storageRepository.deleteById(idStorage);
    return this.getStorages();
  }

}