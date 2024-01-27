package com.holitor.holitorservice.module.blog.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.module.blog.model.dto.CategoryDto;
import com.holitor.holitorservice.module.blog.service.CategoryService;
import com.holitor.holitorservice.exception.ApiException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("${url.api}")
public class CategoryController {

  private @Autowired CategoryService categoryService;

  @PostMapping("/category")
  @ResponseStatus(HttpStatus.CREATED)
  public CategoryDto addCategory(@Valid CategoryDto categoryDto, @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) {
    CategoryDto addedCategoryDto = null;
    try { addedCategoryDto = this.categoryService.addCategory(categoryDto, imageFile); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return addedCategoryDto;
  }

  @GetMapping("/categories")
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryDto> getCategories() { return this.categoryService.getCategories(); }

  @PutMapping("/category")
  @ResponseStatus(HttpStatus.OK)
  public CategoryDto updateCategory(@Valid CategoryDto categoryDto, @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) {
    CategoryDto updatedCategoryDto = null;
    try { updatedCategoryDto = this.categoryService.updateCategory(categoryDto, imageFile); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return updatedCategoryDto;
  }

  @DeleteMapping("/category/{idCategory}")
  @ResponseStatus(HttpStatus.OK)
  public List<CategoryDto> deleteCategory(@PathVariable long idCategory) {
    List<CategoryDto> categories = null;
    try { categories = this.categoryService.deleteCategory(idCategory); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return categories;
  }
  
}