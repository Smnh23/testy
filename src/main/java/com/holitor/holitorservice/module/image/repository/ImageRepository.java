package com.holitor.holitorservice.module.image.repository;

import com.holitor.holitorservice.module.image.model.Image;

import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long> { }