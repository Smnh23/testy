package com.holitor.holitorservice.module.farm.service;

import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.farm.mapper.ParcelMapper;
import com.holitor.holitorservice.module.farm.model.Parcel;
import com.holitor.holitorservice.module.farm.model.dto.ParcelDto;
import com.holitor.holitorservice.module.farm.repository.ParcelRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParcelService {
  
  private static final String PARCEL_NOT_FOUND = "Parcel %s not found !";

  private @Autowired ParcelRepository parcelRepository;
  private @Autowired ParcelMapper parcelMapper;
  
  @Transactional(readOnly = false)
  public ParcelDto addParcel(ParcelDto parcelDto) { 
    Parcel parcel = this.parcelMapper.toEntity(parcelDto);
    return this.parcelMapper.toDto(this.parcelRepository.save(parcel)); 
  }
  
  @Transactional(readOnly = true)
  public List<ParcelDto> getParcels() { 
    List<Parcel> parcels = (List<Parcel>) this.parcelRepository.findAll();
    return parcels.stream().map(parcel -> this.parcelMapper.toDto(parcel)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public ParcelDto updateParcel(ParcelDto parcelDto) { 
    Parcel parcel = this.parcelMapper.toEntity(parcelDto);
    if (!this.parcelRepository.existsById(parcel.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(PARCEL_NOT_FOUND, parcel.getId()));
    return this.parcelMapper.toDto(this.parcelRepository.save(parcel)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<ParcelDto> deleteParcel(long idParcel) { 
    if (!this.parcelRepository.existsById(idParcel)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(PARCEL_NOT_FOUND, idParcel));
    this.parcelRepository.deleteById(idParcel);
    return this.getParcels();
  }

}