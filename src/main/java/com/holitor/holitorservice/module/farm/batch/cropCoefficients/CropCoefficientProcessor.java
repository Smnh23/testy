package com.holitor.holitorservice.module.farm.batch.cropCoefficients;

import com.holitor.holitorservice.module.farm.model.CropCoefficient;
import com.holitor.holitorservice.module.farm.model.Vegetable;
import com.holitor.holitorservice.module.farm.repository.VegetableRepository;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

public class CropCoefficientProcessor implements ItemProcessor<CropCoefficientData, Vegetable> {

  private @Autowired VegetableRepository vegetableRepository;

	@Override
	public Vegetable process(@NonNull CropCoefficientData cropCoefficientData) throws Exception {
    Vegetable vegetable = vegetableRepository.findById(Long.parseLong(cropCoefficientData.getIdVegetable())).get();
    CropCoefficient cropCoefficient = new CropCoefficient();
    cropCoefficient.setNumPeriod(Integer.parseInt(cropCoefficientData.getNumPeriod()));
    cropCoefficient.setStartPeriod(cropCoefficientData.getStartPeriod());
    cropCoefficient.setEndPeriod(cropCoefficientData.getEndPeriod());
    cropCoefficient.setValue(Double.parseDouble(cropCoefficientData.getValue()));
    vegetable.getKcs().add(cropCoefficient);
    return vegetable;
	}

}