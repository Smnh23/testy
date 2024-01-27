package com.holitor.holitorservice.module.farm.batch.botanicalFamilies;

import java.awt.Color;

import com.holitor.holitorservice.module.farm.model.BotanicalFamily;

import java.util.Random;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class BotanicalFamilyProcessor implements ItemProcessor<BotanicalFamilyData, BotanicalFamily> {

	@Override
	public BotanicalFamily process(@NonNull BotanicalFamilyData botanicalFamilyData) throws Exception {
    BotanicalFamily botanicalFamily = new BotanicalFamily();
    botanicalFamily.setColor(this.randomColor());
    botanicalFamily.setName(botanicalFamilyData.getName());
    botanicalFamily.setDescription(botanicalFamilyData.getDescription());
		return botanicalFamily;
	}

  public String randomColor() {
    Random rand = new Random();
    Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
    return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
  }

}