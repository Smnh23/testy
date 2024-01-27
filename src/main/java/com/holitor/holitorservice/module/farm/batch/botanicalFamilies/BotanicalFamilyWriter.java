package com.holitor.holitorservice.module.farm.batch.botanicalFamilies;

import java.util.List;

import com.holitor.holitorservice.module.farm.model.BotanicalFamily;
import com.holitor.holitorservice.module.farm.repository.BotanicalFamilyRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class BotanicalFamilyWriter implements ItemWriter<BotanicalFamily> {

  private @Autowired BotanicalFamilyRepository botanicalFamilyRepository;

	@Override
	public void write(List<? extends BotanicalFamily> botanicalFamilies) throws Exception {
		botanicalFamilies.stream().forEach(botanicalFamily -> botanicalFamilyRepository.save(botanicalFamily));
	}

}