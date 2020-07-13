package com.ttrung.supershop.product.client;


import com.ttrung.supershop.product.dto.PriceCalculationResult;
import com.ttrung.supershop.product.dto.ProductDto;
import com.ttrung.supershop.product.dto.ProductOrderDto;

import java.util.Collection;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
public interface ProductService {

    @GET("/v1/products/{productId}")
    Call<ProductDto> getProduct(@Path("productId") String productId);

    @POST("/v1/products/price-calculation")
    Call<PriceCalculationResult> calculateTotalPrice(@Body Collection<ProductOrderDto> productOrders);
}
