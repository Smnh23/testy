package com.holitor.holitorservice.module.stock.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.holitor.holitorservice.exception.ApiException;
import com.holitor.holitorservice.module.image.model.Image;
import com.holitor.holitorservice.module.stock.mapper.ProductMapper;
import com.holitor.holitorservice.module.stock.model.Product;
import com.holitor.holitorservice.module.stock.model.dto.ProductDto;
import com.holitor.holitorservice.module.stock.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

  private static final String PRODUCT_NOT_FOUND = "Product %s not found !";
  private static final String FILE_ERROR = "File error.";

  private @Autowired ProductRepository productRepository;
  private @Autowired ProductMapper productMapper;

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public ProductDto addProduct(ProductDto productDto, @Nullable MultipartFile imageFile) {
    Product product = this.productMapper.toEntity(productDto);
    if (imageFile != null) {
      try { product.setImage(new Image(imageFile.getOriginalFilename(), imageFile.getContentType(), imageFile.getBytes())); }
      catch (IOException exception) { throw new ApiException(HttpStatus.BAD_REQUEST, FILE_ERROR); }
    } else product.setImage(new Image());
    return this.productMapper.toDto(this.productRepository.save(product));
  }
  
  @Transactional(readOnly = true)
  public List<ProductDto> getProducts() { 
    List<Product> products = (List<Product>) this.productRepository.findAll();
    return products.stream().map(product -> this.productMapper.toDto(product)).collect(Collectors.toList());
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public ProductDto updateProduct(ProductDto productDto, @Nullable MultipartFile imageFile) {
    if (!this.productRepository.existsById(productDto.getId()))
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(PRODUCT_NOT_FOUND, productDto.getId()));
    Product product = this.productMapper.toEntity(productDto);
    Image image = this.productRepository.findById(productDto.getId()).get().getImage();
    if (imageFile != null) {
      try { product.setImage(new Image(image.getId(), imageFile.getOriginalFilename(), imageFile.getContentType(), imageFile.getBytes())); }
      catch (IOException exception) { throw new ApiException(HttpStatus.BAD_REQUEST, FILE_ERROR); }
    } else product.setImage(image);
    return this.productMapper.toDto(this.productRepository.save(product)); 
  }

  @Transactional(rollbackFor = ApiException.class, readOnly = false)
  public List<ProductDto> deleteProduct(long idProduct) { 
    if (!this.productRepository.existsById(idProduct)) 
      throw new ApiException(HttpStatus.NOT_FOUND, String.format(PRODUCT_NOT_FOUND, idProduct));
    this.productRepository.deleteById(idProduct);
    return this.getProducts();
  }

}