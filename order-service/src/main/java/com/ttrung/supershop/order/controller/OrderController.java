/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.controller;

import com.ttrung.supershop.order.dto.OrderDto;
import com.ttrung.supershop.order.service.OrderService;
import com.ttrung.supershop.order.util.AuthenticationExtractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/v1/orders")
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderForm) {
        String userId = AuthenticationExtractor.getAuthenticatedUser();
        log.info("Creating new order for user [{}]", userId);
        OrderDto createdOrder = orderService.createOrder(userId, orderForm);
        return ResponseEntity.ok(createdOrder);
    }
}
