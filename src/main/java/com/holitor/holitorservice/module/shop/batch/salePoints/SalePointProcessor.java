package com.holitor.holitorservice.module.shop.batch.salePoints;

import com.holitor.holitorservice.module.shop.model.SalePoint;

import java.time.LocalTime;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;

public class SalePointProcessor implements ItemProcessor<SalePointData, SalePoint> {

	@Override
	public SalePoint process(@NonNull SalePointData salePointData) throws Exception {
    SalePoint salePoint = new SalePoint();
    salePoint.setActived(true);
    salePoint.setPlace(salePointData.getPlace());
    salePoint.setZipCode(salePointData.getZipCode());
    salePoint.setCity(salePointData.getCity());
    salePoint.setDay((Integer.parseInt(salePointData.getDay())));
    salePoint.setStartTime(LocalTime.parse(salePointData.getStartTime()));
    salePoint.setEndTime(LocalTime.parse(salePointData.getEndTime()));
    salePoint.setLatitude(Double.parseDouble(salePointData.getLatitude()));
    salePoint.setLongitude(Double.parseDouble(salePointData.getLongitude()));
    return salePoint;
	}

}