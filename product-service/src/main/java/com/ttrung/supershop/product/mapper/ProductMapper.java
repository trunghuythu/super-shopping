/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.mapper;

import com.ttrung.supershop.product.domain.Product;
import com.ttrung.supershop.product.dto.ProductDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto domainToDto(Product domain);

    Product dtoToDomain(ProductDto dto);
}
