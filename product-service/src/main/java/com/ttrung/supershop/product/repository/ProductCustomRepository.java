/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.repository;

import com.ttrung.supershop.product.domain.Product;

import org.springframework.data.domain.Page;

public interface ProductCustomRepository {

    Page<Product> findProducts(String searchTerm, String sortBy, String sortDirection, int page, int size);
}
