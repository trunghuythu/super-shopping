package com.ttrung.supershop.product.repository;

import com.ttrung.supershop.product.domain.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
public interface ProductRepository extends MongoRepository<Product, String> {

    Stream<Product> findByIdIn(Set<String> productIds);
}
