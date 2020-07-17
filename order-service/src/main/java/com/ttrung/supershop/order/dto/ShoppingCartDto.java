
package com.ttrung.supershop.order.dto;

import java.util.Set;

import javax.validation.Valid;

@lombok.Getter
@lombok.Setter
public class ShoppingCartDto {
    @Valid
    private Set<ProductOrderDto> productOrders;
}
