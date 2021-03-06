package com.ttrung.supershop.product.service;

import com.ttrung.supershop.product.dto.PriceCalculationResult;
import com.ttrung.supershop.product.dto.ProductDto;
import com.ttrung.supershop.product.dto.ProductOrderDto;

import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.Optional;


public interface ProductService {

    Optional<ProductDto> getProductById(String productId);

    ProductDto createProduct(ProductDto productForm);

    ProductDto updateProduct(String productId, ProductDto productForm);

    Page<ProductDto> getProducts(String searchTerm, String sort, int page, int size);

    PriceCalculationResult calculateTotalPrice(Collection<ProductOrderDto> productOrders);
}
