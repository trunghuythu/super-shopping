
package com.ttrung.supershop.order.repository;

import com.ttrung.supershop.order.domain.ShoppingCart;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends ShoppingCartCustomRepository, MongoRepository<ShoppingCart, String> {
}
