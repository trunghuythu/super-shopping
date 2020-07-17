
package com.ttrung.supershop.product.dto;

import lombok.AllArgsConstructor;

@lombok.Getter
@lombok.Setter
@AllArgsConstructor
public class ProductOrderDto {
    private String productId;
    private int quantity;
}
