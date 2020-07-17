
package com.ttrung.supershop.product.repository;

import com.ttrung.supershop.product.domain.Product;

import org.springframework.data.domain.Page;

public interface ProductCustomRepository {

    Page<Product> findProducts(String searchTerm, String sortBy, String sortDirection, int page, int size);
}
