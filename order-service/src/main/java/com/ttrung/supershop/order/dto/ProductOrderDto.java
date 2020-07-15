/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
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
