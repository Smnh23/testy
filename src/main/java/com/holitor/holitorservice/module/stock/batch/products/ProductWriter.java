package com.holitor.holitorservice.module.stock.batch.products;

import java.util.List;

import com.holitor.holitorservice.module.stock.model.Product;
import com.holitor.holitorservice.module.stock.repository.ProductRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductWriter implements ItemWriter<Product> {

  private @Autowired ProductRepository productRepository;

	@Override
	public void write(List<? extends Product> products) throws Exception {
		products.stream().forEach(product -> productRepository.save(product));
	}

}