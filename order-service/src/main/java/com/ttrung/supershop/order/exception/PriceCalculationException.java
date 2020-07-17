
package com.ttrung.supershop.order.exception;

public class PriceCalculationException extends RuntimeException{

    public PriceCalculationException(String message) {
        super(message);
    }

    public PriceCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
}
