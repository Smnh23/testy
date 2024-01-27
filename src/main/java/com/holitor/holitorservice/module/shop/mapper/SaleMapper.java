package com.holitor.holitorservice.module.shop.mapper;

import com.holitor.holitorservice.module.shop.mapper.config.SaleMapperConfig;
import com.holitor.holitorservice.module.shop.model.Order;
import com.holitor.holitorservice.module.shop.model.Sale;
import com.holitor.holitorservice.module.shop.model.dto.SaleDto;
import com.holitor.holitorservice.module.shop.repository.OrderRepository;
import com.holitor.holitorservice.module.stock.mapper.StorageMapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = SaleMapperConfig.class, uses = StorageMapper.class, componentModel = "spring")
public abstract class SaleMapper {

  @Autowired OrderRepository orderRepository;

  /* ------------------------- Abstract methods ------------------------- */
  
  @InheritConfiguration(name = "mapSale")
  public abstract SaleDto toDto(Sale input);

  @InheritConfiguration(name = "mapSaleDto")
  public abstract Sale toEntity(SaleDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToOrder")
  public final Order idToOrder(long id) { return orderRepository.findById(id).get(); }

}