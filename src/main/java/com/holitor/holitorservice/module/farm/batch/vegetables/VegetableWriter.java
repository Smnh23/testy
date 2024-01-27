package com.holitor.holitorservice.module.farm.batch.vegetables;

import java.util.List;

import com.holitor.holitorservice.module.farm.model.Vegetable;
import com.holitor.holitorservice.module.farm.repository.VegetableRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class VegetableWriter implements ItemWriter<Vegetable> {

  private @Autowired VegetableRepository vegetableRepository;

	@Override
	public void write(List<? extends Vegetable> vegetables) throws Exception {
		vegetables.stream().forEach(vegetable -> vegetableRepository.save(vegetable));
	}

}