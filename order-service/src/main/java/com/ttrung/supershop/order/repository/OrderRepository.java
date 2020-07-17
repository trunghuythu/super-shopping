package com.ttrung.supershop.order.repository;

import com.ttrung.supershop.order.domain.Order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}
