/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.service;

import com.ttrung.supershop.product.domain.Product;
import com.ttrung.supershop.product.dto.ProductDto;
import com.ttrung.supershop.product.exception.ProductNotFoundException;
import com.ttrung.supershop.product.mapper.ProductMapper;
import com.ttrung.supershop.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Optional<ProductDto> getProductById(String productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(productMapper::domainToDto);
    }

    @Override
    public ProductDto createProduct(ProductDto productForm) {
        String productId = UUID.randomUUID().toString();
        productForm.setId(productId);
        Product createdProduct = productRepository.save(productMapper.dtoToDomain(productForm));
        return productMapper.domainToDto(createdProduct);
    }

    @Override
    public ProductDto updateProduct(String productId, ProductDto productForm) {
        if (!productRepository.existsById(productId)) {
            throw new ProductNotFoundException(String.format("The product specified by id [%s] does not exist", productId));
        } else {
            productForm.setId(productId);
            Product updatedProduct = productRepository.save(productMapper.dtoToDomain(productForm));
            return productMapper.domainToDto(updatedProduct);
        }
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(productMapper::domainToDto)
                .collect(Collectors.toList());
    }
}
