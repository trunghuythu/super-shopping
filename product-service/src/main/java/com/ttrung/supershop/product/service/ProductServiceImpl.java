/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.service;

import com.ttrung.supershop.product.domain.Product;
import com.ttrung.supershop.product.dto.ProductDto;
import com.ttrung.supershop.product.mapper.ProductMapper;
import com.ttrung.supershop.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Optional<ProductDto> getProductById(String id) {
        Optional<Product> productContainer = productRepository.findById(id);
        return productContainer.map(productMapper::domainToDto);
    }

    @Override
    public ProductDto createProduct(ProductDto productForm) {
        String productId = UUID.randomUUID().toString();
        productForm.setId(productId);
        Product product = productMapper.dtoToDomain(productForm);
        productRepository.save(product);

        Optional<Product> createdProduct = productRepository.findById(productId);
        return createdProduct.map(productMapper::domainToDto).get();
    }

    @Override
    public ProductDto updateProduct(ProductDto productForm) {
        return null;
    }
}
