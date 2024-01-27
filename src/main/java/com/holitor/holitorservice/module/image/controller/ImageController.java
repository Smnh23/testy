package com.holitor.holitorservice.module.image.controller;

import java.util.List;

import com.holitor.holitorservice.module.image.model.dto.ImageDto;
import com.holitor.holitorservice.module.image.service.ImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${url.api}")
public class ImageController {

  private @Autowired ImageService imageService;

  @GetMapping("/images")
  @ResponseStatus(HttpStatus.OK)
  public List<ImageDto> getImages() { return this.imageService.getImages(); }

}