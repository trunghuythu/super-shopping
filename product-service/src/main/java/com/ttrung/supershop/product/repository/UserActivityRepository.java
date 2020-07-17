package com.ttrung.supershop.product.repository;

import com.ttrung.supershop.product.domain.UserActivity;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
public interface UserActivityRepository extends MongoRepository<UserActivity, String> {
}
