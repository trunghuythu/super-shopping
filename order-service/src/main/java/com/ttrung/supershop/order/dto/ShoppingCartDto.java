/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.dto;

import org.springframework.data.annotation.Id;

import java.util.Set;

import javax.validation.Valid;

@lombok.Getter
@lombok.Setter
public class ShoppingCartDto {
    @Id
    private String id;

    @Valid
    private Set<ProductOrderDto> productOrders;
}
