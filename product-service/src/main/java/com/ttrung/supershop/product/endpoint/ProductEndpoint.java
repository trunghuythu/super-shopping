/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.endpoint;

import com.ttrung.supershop.product.dto.ProductDto;
import com.ttrung.supershop.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class ProductEndpoint {

    @Autowired
    private ProductService productService;

    @GetMapping("/v1/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        ProductDto productDto = new ProductDto();
        productDto.setId(UUID.randomUUID().toString());
        productDto.setName("Super Yogurt");
        productDto.setCategory("Food");
        productDto.setPrice(3.2D);

        return ResponseEntity.ok(Collections.singletonList(productDto));
    }

    @GetMapping("/v1/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String id) {
        Optional<ProductDto> productContainer = productService.getProductById(id);

        return productContainer.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(NOT_FOUND).body(null));
    }

    @PostMapping("/v1/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productForm) {
        ProductDto createdProduct = productService.createProduct(productForm);
        return ResponseEntity.ok(createdProduct);
    }

    @PutMapping("/v1/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String id, @RequestBody ProductDto productForm) {
        ProductDto createdProduct = productService.updateProduct(productForm);
        return ResponseEntity.ok(createdProduct);
    }
}
