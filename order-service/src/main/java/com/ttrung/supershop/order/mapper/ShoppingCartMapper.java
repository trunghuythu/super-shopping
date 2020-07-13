/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.mapper;

import com.ttrung.supershop.order.domain.ShoppingCart;
import com.ttrung.supershop.order.dto.ShoppingCartDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {

    ShoppingCartDto domainToDto(ShoppingCart domain);

    ShoppingCart dtoToDomain(ShoppingCartDto dto);
}
