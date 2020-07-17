
package com.ttrung.supershop.order.mapper;

import com.ttrung.supershop.order.domain.Order;
import com.ttrung.supershop.order.dto.OrderDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto domainToDto(Order domain);

    Order dtoToDomain(OrderDto dto);
}
