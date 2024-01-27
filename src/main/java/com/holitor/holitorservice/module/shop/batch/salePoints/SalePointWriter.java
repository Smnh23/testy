package com.holitor.holitorservice.module.shop.batch.salePoints;

import java.util.List;

import com.holitor.holitorservice.module.shop.model.SalePoint;
import com.holitor.holitorservice.module.shop.repository.SalePointRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class SalePointWriter implements ItemWriter<SalePoint> {

  private @Autowired SalePointRepository salePointRepository;

	@Override
	public void write(List<? extends SalePoint> salePoints) throws Exception {
		salePoints.stream().forEach(salePoint -> salePointRepository.save(salePoint));
	}

}