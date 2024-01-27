package com.holitor.holitorservice.module.stock.controller;

import java.util.List;

import javax.validation.Valid;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.stock.model.dto.ProductDto;
import com.holitor.holitorservice.module.stock.service.ProductService;

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
@RequestMapping({"${url.api}"})
public class ProductController {

  private @Autowired ProductService productService;

  @PostMapping("/product")
  @ResponseStatus(HttpStatus.CREATED)
  public ProductDto addProduct(@Valid ProductDto productDto, @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) {
    ProductDto addedProductDto = null;
    try { addedProductDto = this.productService.addProduct(productDto, imageFile); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return addedProductDto;
  }

  @GetMapping("/products")
  @ResponseStatus(HttpStatus.OK)
  public List<ProductDto> getProducts() { return this.productService.getProducts(); }

  @PutMapping("/product")
  @ResponseStatus(HttpStatus.OK)
  public ProductDto updateProduct(@Valid ProductDto productDto, @RequestParam(name = "imageFile", required = false) MultipartFile imageFile) {
    ProductDto updatedProductDto = null;
    try { updatedProductDto = this.productService.updateProduct(productDto, imageFile); }
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return updatedProductDto;
  }

  @DeleteMapping("/product/{idProduct}")
  @ResponseStatus(HttpStatus.OK)
  public List<ProductDto> deleteProduct(@PathVariable long idProduct) {
    List<ProductDto> products = null;
    try { products = this.productService.deleteProduct(idProduct); } 
    catch (ApiException exception) { throw new ResponseStatusException(exception.getCode(), exception.getMessage(), exception); }
    return products;
  }

}