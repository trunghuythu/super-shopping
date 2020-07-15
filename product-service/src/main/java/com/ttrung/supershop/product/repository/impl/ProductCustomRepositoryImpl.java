/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.repository.impl;

import com.ttrung.supershop.product.domain.Product;
import com.ttrung.supershop.product.repository.ProductCustomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public Page<Product> findProducts(String searchTerm, String sortBy, String sortDirection, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        PageRequest pageRequest = PageRequest.of(page, size);
        Criteria criteria = new Criteria().orOperator(
                Criteria.where(Product.NAME_FIELD).regex(buildSearchReg(searchTerm), "i"),
                Criteria.where(Product.CATEGORY_FIELD).regex(buildSearchReg(searchTerm), "i"),
                Criteria.where(Product.BRAND_FIELD).regex(buildSearchReg(searchTerm), "i")
        );

        Query query = Query.query(criteria)
                .with(sort)
                .with(pageRequest);

        List<Product> products = mongoOperations.find(query, Product.class);
        return PageableExecutionUtils.getPage(products, pageRequest, () -> mongoOperations.count(query, Product.class));
    }

    private String buildSearchReg(String searchTerm) {
        return new StringBuilder(".*")
                .append(searchTerm)
                .append(".*")
                .toString();
    }
}
