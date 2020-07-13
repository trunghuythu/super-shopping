/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.repository;

import com.ttrung.supershop.order.domain.ShoppingCart;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShoppingCartRepository extends ShoppingCartCustomRepository, MongoRepository<ShoppingCart, String> {
}
