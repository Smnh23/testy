package com.holitor.holitorservice.module.farm.batch.fertilizers;

import java.util.List;

import com.holitor.holitorservice.module.farm.model.Fertilizer;
import com.holitor.holitorservice.module.farm.repository.FertilizerRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class FertilizerWriter implements ItemWriter<Fertilizer> {

  private @Autowired FertilizerRepository fertilizerRepository;

	@Override
	public void write(List<? extends Fertilizer> fertilizers) throws Exception {
		fertilizers.stream().forEach(fertilizer -> fertilizerRepository.save(fertilizer));
	}

}