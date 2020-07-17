
package com.ttrung.supershop.order.service.impl;

import com.ttrung.supershop.order.domain.ShoppingCart;
import com.ttrung.supershop.order.dto.ShoppingCartDto;
import com.ttrung.supershop.order.mapper.ShoppingCartMapper;
import com.ttrung.supershop.order.repository.ShoppingCartRepository;
import com.ttrung.supershop.order.service.ShoppingCartService;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private ShoppingCartRepository cartRepository;
    private ShoppingCartMapper cartMapper;

    public ShoppingCartServiceImpl(ShoppingCartRepository cartRepository, ShoppingCartMapper cartMapper) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
    }

    @Override
    public ShoppingCartDto updateCart(String userId, ShoppingCartDto cartForm) {
        ShoppingCart cart = cartMapper.dtoToDomain(cartForm);
        cart.setId(userId);
        ShoppingCart updatedCart = cartRepository.save(cart);

        return cartMapper.domainToDto(updatedCart);
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
