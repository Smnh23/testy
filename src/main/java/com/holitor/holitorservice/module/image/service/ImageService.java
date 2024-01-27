package com.holitor.holitorservice.module.image.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.image.mapper.ImageMapper;
import com.holitor.holitorservice.module.image.model.Image;
import com.holitor.holitorservice.module.image.model.dto.ImageDto;
import com.holitor.holitorservice.module.image.repository.ImageRepository;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageService {

  private @Autowired ImageRepository imageRepository;
  private @Autowired ImageMapper imageMapper;

  public static Image readImage(File file) {
    Image image = new Image();
    image.setName(file.getName());
    image.setType("image/"+FilenameUtils.getExtension(file.getPath()));
    try {
      InputStream input = new FileInputStream(file);
      image.setPic(IOUtils.toByteArray(input));
    } catch (Exception exception) { throw new ApiException(HttpStatus.BAD_REQUEST, "Erreur fichier."); }
    return image;
  }

  @Transactional(readOnly = true)
  public List<ImageDto> getImages() { 
    List<Image> images = (List<Image>) this.imageRepository.findAll();
    return images.stream().map(image -> this.imageMapper.toDto(image)).collect(Collectors.toList());
  }

}