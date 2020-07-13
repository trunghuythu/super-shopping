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

import java.util.List;

@lombok.Getter
@lombok.Setter
@Document
public class Order {
    public static final String USER_ID_FIELD = "userId";
    public static final String ORDER_ID_FIELD = "_id";
    public static final String ORDER_STATUS_FIELD = "status";

    @Id
    private String id;

    @Field(USER_ID_FIELD)
    private String userId;
    @Field(ORDER_STATUS_FIELD)
    private OrderStatus status;
    private List<ProductOrder> productOrders;
    private String deliveryAddress;
    private Double totalPrice;
}
