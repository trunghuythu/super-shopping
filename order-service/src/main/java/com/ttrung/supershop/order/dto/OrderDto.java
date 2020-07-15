/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ttrung.supershop.order.domain.OrderStatus;

import org.springframework.data.annotation.Id;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@lombok.Getter
@lombok.Setter
public class OrderDto {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OrderStatus status;

    @NotEmpty
    @Valid
    private List<ProductOrderDto> productOrders;

    @NotBlank
    private String deliveryAddress;

    private Double totalPrice;
}
