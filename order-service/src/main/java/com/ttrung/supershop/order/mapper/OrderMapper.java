/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.mapper;

import com.ttrung.supershop.order.domain.Order;
import com.ttrung.supershop.order.dto.OrderDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderDto domainToDto(Order domain);

    Order dtoToDomain(OrderDto dto);
}
