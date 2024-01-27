package com.holitor.holitorservice.module.stock.mapper;

import java.util.Optional;

import com.holitor.holitorservice.module.farm.model.Unit;
import com.holitor.holitorservice.module.farm.model.Variety;
import com.holitor.holitorservice.module.farm.repository.UnitRepository;
import com.holitor.holitorservice.module.farm.repository.VarietyRepository;
import com.holitor.holitorservice.module.image.mapper.ImageMapper;
import com.holitor.holitorservice.module.stock.mapper.config.ProductMapperConfig;
import com.holitor.holitorservice.module.stock.model.Product;
import com.holitor.holitorservice.module.stock.model.dto.ProductDto;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = ProductMapperConfig.class, uses = ImageMapper.class, componentModel = "spring")
public abstract class ProductMapper {
  
  @Autowired UnitRepository unitRepository;
  @Autowired VarietyRepository varietyRepository;

  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapProduct")
  public abstract ProductDto toDto(Product input);

  @InheritConfiguration(name = "mapProductDto")
  public abstract Product toEntity(ProductDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToUnit")
  public final Unit idToUnit(long id) { return unitRepository.findById(id).get(); }

  @Named("idToVariety")
  public final Variety idToVariety(Long id) { 
    Optional<Variety> optional = varietyRepository.findById(id);
    return (optional.isPresent()) ? optional.get() : null;
  }

}