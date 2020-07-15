package com.ttrung.supershop.order.service;

import com.ttrung.supershop.order.dto.OrderDto;

/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
public interface OrderService {

    OrderDto createOrder(String userId, OrderDto orderForm);
}
