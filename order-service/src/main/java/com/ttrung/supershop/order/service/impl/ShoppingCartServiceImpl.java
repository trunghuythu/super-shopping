/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.service.impl;

import com.ttrung.supershop.order.domain.ShoppingCart;
import com.ttrung.supershop.order.dto.ShoppingCartDto;
import com.ttrung.supershop.order.mapper.ShoppingCartMapper;
import com.ttrung.supershop.order.repository.ShoppingCartRepository;
import com.ttrung.supershop.order.service.ShoppingCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository cartRepository;

    @Autowired
    private ShoppingCartMapper cartMapper;

    @Override
    public ShoppingCartDto updateCart(String userId, ShoppingCartDto cartForm) {
        cartForm.setId(userId);
        ShoppingCart updatedCart = cartRepository.save(cartMapper.dtoToDomain(cartForm));

        return cartMapper.domainToDto(updatedCart);
    }

    @Override
    public boolean createCart(String userId) {
        if (cartRepository.existsById(userId)) {
            return false;
        } else {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setId(userId);
            cartRepository.save(shoppingCart);
            return true;
        }
    }

    @Override
    public Optional<ShoppingCartDto> getCart(String userId) {
        Optional<ShoppingCart> cart = cartRepository.findById(userId);
        return cart.map(cartMapper::domainToDto);
    }

    @Override
    public void clearCart(String userId) {
        cartRepository.clearCart(userId);
    }
}
