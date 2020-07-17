
package com.ttrung.supershop.product.mapper;

import com.ttrung.supershop.product.domain.Product;
import com.ttrung.supershop.product.dto.ProductDto;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto domainToDto(Product domain);

    Product dtoToDomain(ProductDto dto);
}
