package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.HarvestMapperConfig;
import com.holitor.holitorservice.module.farm.model.Harvest;
import com.holitor.holitorservice.module.farm.model.Production;
import com.holitor.holitorservice.module.farm.model.ProductionKey;
import com.holitor.holitorservice.module.farm.model.dto.HarvestDto;
import com.holitor.holitorservice.module.farm.model.dto.ProductionKeyDto;
import com.holitor.holitorservice.module.farm.repository.ProductionRepository;
import com.holitor.holitorservice.module.stock.mapper.StorageMapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = HarvestMapperConfig.class, uses = StorageMapper.class, componentModel = "spring")
public abstract class HarvestMapper {

  @Autowired ProductionRepository productionRepository;

  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapHarvest")
  public abstract HarvestDto toDto(Harvest input);

  @InheritConfiguration(name = "mapHarvestDto")
  public abstract Harvest toEntity(HarvestDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToProduction")
  public final Production idToProduction(ProductionKeyDto id) {
    ProductionKey key = new ProductionKey();
    key.setVarietyId(id.getVarietyId());
    key.setSerieId(id.getSerieId());
    return productionRepository.findById(key);
  }

}