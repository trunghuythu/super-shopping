package com.ttrung.supershop.order.service;

import com.ttrung.supershop.order.dto.OrderDto;


public interface OrderService {

    OrderDto createOrder(String userId, OrderDto orderForm);
}
