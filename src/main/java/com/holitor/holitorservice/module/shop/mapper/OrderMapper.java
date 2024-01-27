package com.holitor.holitorservice.module.shop.mapper;

import com.holitor.holitorservice.module.shop.model.dto.OrderDto;
import com.holitor.holitorservice.module.shop.repository.SalePointRepository;
import com.holitor.holitorservice.module.user.model.User;
import com.holitor.holitorservice.module.user.repository.UserRepository;
import com.holitor.holitorservice.module.shop.mapper.config.OrderMapperConfig;
import com.holitor.holitorservice.module.shop.model.Order;
import com.holitor.holitorservice.module.shop.model.SalePoint;

import java.util.Optional;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(config = OrderMapperConfig.class, uses = ItemMapper.class, componentModel = "spring")
public abstract class OrderMapper {

  @Autowired UserRepository userRepository;
  @Autowired SalePointRepository salePointRepository;

  /* ------------------------- Abstract methods ------------------------- */

  @InheritConfiguration(name = "mapOrder")
  public abstract OrderDto toDto(Order input);

  @InheritConfiguration(name = "mapOrderDto")
  public abstract Order toEntity(OrderDto input);

  /* ------------------------- Final methods ------------------------- */

  @Named("idToUser")
  public final User idToUser(long id) { return userRepository.findById(id).get(); }

  @Named("idToSalePoint")
  public final SalePoint idToSalePoint(long id) {
    Optional<SalePoint> salePointOptional = salePointRepository.findById(id);
    return (salePointOptional.isPresent()) ? salePointOptional.get() : null;
  }
  
}