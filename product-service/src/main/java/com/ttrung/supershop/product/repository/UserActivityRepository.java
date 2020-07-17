package com.ttrung.supershop.product.repository;

import com.ttrung.supershop.product.domain.UserActivity;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserActivityRepository extends MongoRepository<UserActivity, String> {
}
