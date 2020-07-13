/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.controller;

import com.ttrung.supershop.order.dto.ShoppingCartDto;
import com.ttrung.supershop.order.exception.CartNotFoundException;
import com.ttrung.supershop.order.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

@RestController
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PutMapping("v1/shopping-carts/{userId}")
    public ResponseEntity<ShoppingCartDto> updateCart(@PathVariable("userId") String userId,@Valid @RequestBody ShoppingCartDto cartForm) {
        try {
            ShoppingCartDto shoppingCartDto = shoppingCartService.updateCart(userId, cartForm);
            return ResponseEntity.ok(shoppingCartDto);
        } catch (CartNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("v1/shopping-carts/{userId}")
    public ResponseEntity<ShoppingCartDto> getCart(@PathVariable("userId") String userId) {
        Optional<ShoppingCartDto> shoppingCart = shoppingCartService.getCart(userId);
        return shoppingCart.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
