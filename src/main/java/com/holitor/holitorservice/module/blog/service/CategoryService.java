package com.holitor.holitorservice.module.blog.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.module.blog.mapper.CategoryMapper;
import com.holitor.holitorservice.module.blog.model.Category;
import com.holitor.holitorservice.module.blog.model.dto.CategoryDto;
import com.holitor.holitorservice.module.blog.repository.CategoryRepository;
import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.image.model.Image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CategoryService {

  private static final String CATEGORY_NOT_FOUND = "Category %s not found !";
  private static final String FILE_ERROR = "File error.";

  private @Autowired CategoryRepository categoryRepository;
  private @Autowired CategoryMapper categoryMapper;

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public CategoryDto addCategory(CategoryDto categoryDto, @Nullable MultipartFile imageFile) {
    Category category = this.categoryMapper.toEntity(categoryDto);
    if (imageFile != null) {
      try { category.setImage(new Image(imageFile.getOriginalFilename(), imageFile.getContentType(), imageFile.getBytes())); }
      catch (IOException exception) { throw new ApiException(HttpStatus.BAD_REQUEST, FILE_ERROR); }
    } else category.setImage(new Image());
    return this.categoryMapper.toDto(this.categoryRepository.save(category));
  }

  @Transactional(readOnly = true)
  public List<CategoryDto> getCategories() { 
    List<Category> categorys = (List<Category>) this.categoryRepository.findAll();
    return categorys.stream().map(category -> this.categoryMapper.toDto(category)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public CategoryDto updateCategory(CategoryDto categoryDto, @Nullable MultipartFile imageFile) {
    if (!this.categoryRepository.existsById(categoryDto.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(CATEGORY_NOT_FOUND, categoryDto.getId()));
    Category category = this.categoryMapper.toEntity(categoryDto);
    Image image = this.categoryRepository.findById(categoryDto.getId()).get().getImage();
    if (imageFile != null) {
      try { category.setImage(new Image(image.getId(), imageFile.getOriginalFilename(), imageFile.getContentType(), imageFile.getBytes())); }
      catch (IOException exception) { throw new ApiException(HttpStatus.BAD_REQUEST, FILE_ERROR); }
    } else category.setImage(image);
    return this.categoryMapper.toDto(this.categoryRepository.save(category)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<CategoryDto> deleteCategory(long idCategory) { 
    if (!this.categoryRepository.existsById(idCategory)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(CATEGORY_NOT_FOUND, idCategory));
    this.categoryRepository.deleteById(idCategory);
    return this.getCategories();
  }

}