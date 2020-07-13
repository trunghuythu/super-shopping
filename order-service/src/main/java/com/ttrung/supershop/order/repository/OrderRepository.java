package com.ttrung.supershop.order.repository;

import com.ttrung.supershop.order.domain.Order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
@Repository
public interface OrderRepository extends OrderCustomRepository, MongoRepository<Order, String> {
}
