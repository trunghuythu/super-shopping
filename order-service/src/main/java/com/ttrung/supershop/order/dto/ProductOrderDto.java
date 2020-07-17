
package com.ttrung.supershop.order.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@lombok.Getter
@lombok.Setter
public class ProductOrderDto {
    @NotBlank
    private String productId;
    @Min(1)
    private int quantity;
}
