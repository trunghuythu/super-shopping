/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.repository.impl;

import com.mongodb.client.result.UpdateResult;
import com.ttrung.supershop.order.domain.Order;
import com.ttrung.supershop.order.domain.OrderStatus;
import com.ttrung.supershop.order.repository.OrderCustomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public boolean cancelOrder(String userId, String orderId) {
        Criteria criteria = Criteria.where(Order.USER_ID_FIELD).is(userId)
                .and(Order.ORDER_ID_FIELD).is(orderId);

        Update update = new Update();
        update.set(Order.ORDER_STATUS_FIELD, OrderStatus.CANCELLED);

        UpdateResult updateResult = mongoOperations.updateFirst(Query.query(criteria), update, Order.class);
        return updateResult.getModifiedCount() == 1;
    }
}
