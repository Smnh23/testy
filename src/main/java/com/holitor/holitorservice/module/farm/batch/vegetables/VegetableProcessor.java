package com.holitor.holitorservice.module.farm.batch.vegetables;

import java.awt.Color;

import java.util.ArrayList;
import java.util.Random;

import com.holitor.holitorservice.module.farm.model.Variety;
import com.holitor.holitorservice.module.farm.model.Vegetable;
import com.holitor.holitorservice.module.farm.model.Vegetable.Irrigation;
import com.holitor.holitorservice.module.farm.repository.BotanicalFamilyRepository;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

public class VegetableProcessor implements ItemProcessor<VegetableData, Vegetable> {

  private @Autowired BotanicalFamilyRepository botanicalFamilyRepository;

	@Override
	public Vegetable process(@NonNull VegetableData vegetableData) throws Exception {
    Vegetable vegetable = new Vegetable();
    vegetable.setBotanicalFamily(botanicalFamilyRepository.findById(Long.parseLong(vegetableData.getFamily())).get());
    vegetable.setName(vegetableData.getName());
    vegetable.setTaxonomy(vegetableData.getTaxonomy());
    vegetable.setColor(this.randomColor());
    vegetable.setSpacingPlant(Double.parseDouble(vegetableData.getSpacingPlant()));
    vegetable.setSpacingRank(Double.parseDouble(vegetableData.getSpacingRank()));
    vegetable.setYield(Double.parseDouble(vegetableData.getYield()));
    vegetable.setExportN(Double.parseDouble(vegetableData.getExportN()));
    vegetable.setExportP(Double.parseDouble(vegetableData.getExportP()));
    vegetable.setExportK(Double.parseDouble(vegetableData.getExportK()));
    vegetable.setFertiN(Double.parseDouble(vegetableData.getFertiN()));
    vegetable.setFertiP(Double.parseDouble(vegetableData.getFertiP()));
    vegetable.setFertiK(Double.parseDouble(vegetableData.getFertiK()));
    vegetable.setPrice(Double.parseDouble(vegetableData.getPrice()));
    vegetable.setPiece(Boolean.parseBoolean(vegetableData.getPiece()));
    vegetable.setPlanning(vegetableData.getPlanning());
    vegetable.setIrrigation(Irrigation.NONE);
    vegetable.setVarieties(new ArrayList<Variety>());
    return vegetable;
  }

  public String randomColor() {
    Random rand = new Random();
    Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
  }

}