
package com.ttrung.supershop.order.repository.impl;

import com.ttrung.supershop.order.domain.ShoppingCart;
import com.ttrung.supershop.order.repository.ShoppingCartCustomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collections;

@Repository
public class ShoppingCartCustomRepositoryImpl implements ShoppingCartCustomRepository {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void clearCart(String userId) {
        Criteria criteria = Criteria.where(ShoppingCart.ID_FIELD).is(userId);

        Update update = new Update();
        update.set(ShoppingCart.PRODUCT_ORDERS_FIELD, Collections.emptyList());

        mongoOperations.updateFirst(Query.query(criteria), update, ShoppingCart.class);
    }
}
