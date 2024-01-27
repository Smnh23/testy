package com.holitor.holitorservice.module.farm.batch.pesticides;

import com.holitor.holitorservice.module.farm.model.Pesticide;
import com.holitor.holitorservice.module.farm.model.FarmInput.FarmInputType;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class PesticideProcessor implements ItemProcessor<PesticideData, Pesticide> {

	@Override
	public Pesticide process(@NonNull PesticideData pesticideData) throws Exception {
    Pesticide pesticide = new Pesticide();
    pesticide.setName(pesticideData.getName());
    pesticide.setBrand(pesticideData.getBrand());
    pesticide.setPrice(Double.parseDouble(pesticideData.getPrice()));
    pesticide.setAb(Boolean.parseBoolean(pesticideData.getAb()));
    pesticide.setType(FarmInputType.PESTICIDE);
		return pesticide;
	}

}