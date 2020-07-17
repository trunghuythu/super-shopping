package com.ttrung.supershop.product.repository;

import com.ttrung.supershop.product.domain.Product;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;

/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
@JaversSpringDataAuditable
public interface ProductRepository extends ProductCustomRepository, MongoRepository<Product, String> {

    List<Product> findByIdIn(Set<String> productIds);
}
