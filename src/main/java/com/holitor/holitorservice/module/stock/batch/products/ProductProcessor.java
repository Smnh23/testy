package com.holitor.holitorservice.module.stock.batch.products;

import com.holitor.holitorservice.module.farm.model.Unit;
import com.holitor.holitorservice.module.farm.repository.UnitRepository;
import com.holitor.holitorservice.module.farm.repository.VarietyRepository;
import com.holitor.holitorservice.module.image.service.ImageService;
import com.holitor.holitorservice.module.stock.model.Product;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.util.ResourceUtils;

public class ProductProcessor implements ItemProcessor<ProductData, Product> {

  private @Autowired VarietyRepository varietyRepository;
  private @Autowired UnitRepository unitRepository;

	@Override
	public Product process(@NonNull ProductData productData) throws Exception {
    Product product = new Product();
    product.setVariety(varietyRepository.findById(Long.parseLong(productData.getIdVariety())).get());
    product.setName(product.getVariety().getVegetable().getName());
    product.setDescription(productData.getDescription());
    product.setPrice(Double.parseDouble(productData.getPrice()));
    product.setImage(ImageService.readImage(ResourceUtils.getFile("classpath:"+productData.getImage())));
    Unit unit = unitRepository.findByNameAndFactor(productData.getUnitName(), Double.parseDouble(productData.getUnitFactor()));
    if (unit == null) {
      unit = new Unit();
      unit.setName(productData.getUnitName());
      unit.setFactor(Double.parseDouble(productData.getUnitFactor()));
      unit.setPiece(unit.getName().compareTo("pièce") == 0);
      this.unitRepository.save(unit);
    } product.setUnit(unit);
    return product;
	}

}