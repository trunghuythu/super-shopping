/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@lombok.Getter
@lombok.Setter
@Document
public class ShoppingCart {
    public static final String ID_FIELD = "_id";
    public static final String PRODUCT_ORDERS_FIELD = "productOrders";

    //Id of the user who owns the cart
    @Id
    private String id;

    @Field(PRODUCT_ORDERS_FIELD)
    private Set<ProductOrder> productOrders;
}
