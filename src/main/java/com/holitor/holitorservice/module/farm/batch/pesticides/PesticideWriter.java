package com.holitor.holitorservice.module.farm.batch.pesticides;

import java.util.List;

import com.holitor.holitorservice.module.farm.model.Pesticide;
import com.holitor.holitorservice.module.farm.repository.PesticideRepository;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

public class PesticideWriter implements ItemWriter<Pesticide> {

  private @Autowired PesticideRepository pesticideRepository;

	@Override
	public void write(List<? extends Pesticide> pesticides) throws Exception {
		pesticides.stream().forEach(pesticide -> pesticideRepository.save(pesticide));
	}

}