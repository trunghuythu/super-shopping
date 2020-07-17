
package com.ttrung.supershop.order.mapper;

import com.ttrung.supershop.order.domain.ShoppingCart;
import com.ttrung.supershop.order.dto.ShoppingCartDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {

    ShoppingCartDto domainToDto(ShoppingCart domain);

    ShoppingCart dtoToDomain(ShoppingCartDto dto);
}
