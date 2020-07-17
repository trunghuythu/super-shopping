
package com.ttrung.supershop.product.dto;

@lombok.Getter
@lombok.Setter
public class PriceCalculationResult {

    private Double totalPrice;

    public PriceCalculationResult(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
