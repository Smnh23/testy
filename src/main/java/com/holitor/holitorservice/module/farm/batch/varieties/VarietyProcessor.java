package com.holitor.holitorservice.module.farm.batch.varieties;

import com.holitor.holitorservice.module.farm.model.Variety;
import com.holitor.holitorservice.module.farm.model.Vegetable;
import com.holitor.holitorservice.module.farm.repository.VegetableRepository;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

public class VarietyProcessor implements ItemProcessor<VarietyData, Vegetable> {

  private @Autowired VegetableRepository vegetableRepository;

	@Override
	public Vegetable process(@NonNull VarietyData varietyData) throws Exception {
    Vegetable vegetable = vegetableRepository.findById(Long.parseLong(varietyData.getIdVegetable())).get();
    Variety variety = new Variety();
    variety.setName(varietyData.getName());
    variety.setHybridF1(Boolean.parseBoolean(varietyData.getHybridF1()));
    variety.setPrice(Double.parseDouble(varietyData.getPrice()));
    variety.setPlanning(varietyData.getPlanning());
    variety.setVegetable(vegetable);
    vegetable.getVarieties().add(variety);
    return vegetable;
	}

}