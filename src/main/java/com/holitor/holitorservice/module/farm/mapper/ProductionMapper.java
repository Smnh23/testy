package com.holitor.holitorservice.module.farm.mapper;

import com.holitor.holitorservice.module.farm.mapper.config.ProductionMapperConfig;
import com.holitor.holitorservice.module.farm.model.Production;
import com.holitor.holitorservice.module.farm.model.Serie;
import com.holitor.holitorservice.module.farm.model.Variety;
import com.holitor.holitorservice.module.farm.model.dto.ProductionDto;
import com.holitor.holitorservice.module.farm.repository.SerieRepository;
import com.holitor.holitorservice.module.farm.repository.VarietyRepository;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = ProductionMapperConfig.class, uses = SeedlingMapper.class, componentModel = "spring")
public abstract class ProductionMapper {

  @Autowired SerieRepository serieRepository;
  @Autowired VarietyRepository varietyRepository;

  @InheritConfiguration(name = "mapProduction")
  public abstract ProductionDto toDto(Production input);

  @InheritConfiguration(name = "mapProductionDto")
  public abstract Production toEntity(ProductionDto input);
  
  /* ------------------------- Final methods ------------------------- */

  @Named("idToSerie")
  public final Serie idToSerie(long id) { return serieRepository.findById(id).get(); }

  @Named("idToVariety")
  public final Variety idToVariety(long id) { return varietyRepository.findById(id).get(); }
  
}