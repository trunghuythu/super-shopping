package com.ttrung.supershop.product.service;

import com.ttrung.supershop.product.domain.Product;
import com.ttrung.supershop.product.dto.ProductDto;

import java.util.Optional;

/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
public interface ProductService {

    Optional<ProductDto> getProductById(String id);

    ProductDto createProduct(ProductDto productForm);

    ProductDto updateProduct(ProductDto productForm);
}
