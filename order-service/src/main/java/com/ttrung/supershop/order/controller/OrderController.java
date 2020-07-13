/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.controller;

import com.ttrung.supershop.order.dto.OrderDto;
import com.ttrung.supershop.order.exception.OrderNotFoundException;
import com.ttrung.supershop.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/v1/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestHeader("userId") String userId,
                                                @Valid @RequestBody OrderDto orderForm) {
        log.info("Creating new order for user [{}]", userId);
        OrderDto createdOrder = orderService.createOrder(userId, orderForm);
        return ResponseEntity.ok(createdOrder);
    }

    @DeleteMapping("/v1/orders/{orderId}")
    public ResponseEntity<?> cancelOrder(@PathVariable("orderId") String orderId,
                                         @RequestHeader("userId") String userId) {
        try {
            log.info("Cancelling order [{}] from user [{}]", orderId, userId);
            orderService.cancelOrder(userId, orderId);
        } catch (OrderNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/v1/orders/{orderId}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("orderId") String orderId,
                                                @RequestHeader("userId") String userId,
                                                @Valid @RequestBody OrderDto orderForm) {
        log.info("Updating order [{}] of user [{}]",orderId, userId);
        OrderDto createdOrder = orderService.updateOrder(userId, orderId, orderForm);
        return ResponseEntity.ok(createdOrder);
    }
}
