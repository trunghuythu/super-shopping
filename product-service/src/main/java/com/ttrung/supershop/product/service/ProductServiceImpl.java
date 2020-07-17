/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.service;

import com.ttrung.supershop.product.annotation.RecordProductSearch;
import com.ttrung.supershop.product.domain.Product;
import com.ttrung.supershop.product.dto.PriceCalculationResult;
import com.ttrung.supershop.product.dto.ProductDto;
import com.ttrung.supershop.product.dto.ProductOrderDto;
import com.ttrung.supershop.product.exception.ProductNotFoundException;
import com.ttrung.supershop.product.mapper.ProductMapper;
import com.ttrung.supershop.product.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

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
    @RecordProductSearch
    public Page<ProductDto> getProducts(String searchTerm, String sort, int page, int size) {
        String[] sortSpec = sort.split(",");
        String sortBy = sortSpec[0].trim();
        String sortDirection = sortSpec[1].trim();

        Page<Product> pagedResult = productRepository.findProducts(searchTerm, sortBy, sortDirection, page, size);
        List<ProductDto> products = pagedResult.get()
                .map(productMapper::domainToDto)
                .collect(Collectors.toList());

        return new PageImpl<>(products, pagedResult.getPageable(), pagedResult.getTotalElements());
    }

    @Override
    public PriceCalculationResult calculateTotalPrice(Collection<ProductOrderDto> productOrders) {
        //TODO : Use Mongo aggregation to calculate the total price to utilize mongo aggregation power
        Set<String> productIds = productOrders.stream()
                .map(ProductOrderDto::getProductId)
                .collect(Collectors.toSet());

        List<Product> products = productRepository.findByIdIn(productIds);
        double totalPrice = products
                .stream()
                .mapToDouble(Product::getPrice).sum();
        return new PriceCalculationResult(totalPrice);
    }
}
