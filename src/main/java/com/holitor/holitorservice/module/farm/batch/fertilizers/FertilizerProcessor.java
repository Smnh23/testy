package com.holitor.holitorservice.module.farm.batch.fertilizers;

import com.holitor.holitorservice.module.farm.model.Fertilizer;
import com.holitor.holitorservice.module.farm.model.FarmInput.FarmInputType;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class FertilizerProcessor implements ItemProcessor<FertilizerData, Fertilizer> {

	@Override
	public Fertilizer process(@NonNull FertilizerData fertilizerData) throws Exception {
    Fertilizer fertilizer = new Fertilizer();
    fertilizer.setName(fertilizerData.getName());
    fertilizer.setBrand(fertilizerData.getBrand());
    fertilizer.setPrice(Double.parseDouble(fertilizerData.getPrice()));
    fertilizer.setNitrogen(Double.parseDouble(fertilizerData.getNitrogen()));
    fertilizer.setPhosphorus(Double.parseDouble(fertilizerData.getPhosphorus()));
    fertilizer.setPotassium(Double.parseDouble(fertilizerData.getPotassium()));
    fertilizer.setAb(Boolean.parseBoolean(fertilizerData.getAb()));
    fertilizer.setType(FarmInputType.FERTILIZER);
		return fertilizer;
	}

}