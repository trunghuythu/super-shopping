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


public interface ProductService {

    @GET("/v1/products/{productId}")
    Call<ProductDto> getProduct(@Path("productId") String productId);

    @POST("/v1/products/price-calculation")
    Call<PriceCalculationResult> calculateTotalPrice(@Body Collection<ProductOrderDto> productOrders);
}
