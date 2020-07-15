package com.ttrung.supershop.order.service;

import com.ttrung.supershop.order.dto.ShoppingCartDto;

import java.util.Optional;

public interface ShoppingCartService {

    ShoppingCartDto updateCart(String userId, ShoppingCartDto cartForm);

    Optional<ShoppingCartDto> getCart(String userId);

    void clearCart(String userId);
}
