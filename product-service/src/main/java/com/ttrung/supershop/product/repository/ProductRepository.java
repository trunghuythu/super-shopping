package com.ttrung.supershop.product.repository;

import com.ttrung.supershop.product.domain.Product;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;


@JaversSpringDataAuditable
public interface ProductRepository extends ProductCustomRepository, MongoRepository<Product, String> {

    List<Product> findByIdIn(Set<String> productIds);
}
