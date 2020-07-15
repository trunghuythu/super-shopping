/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.order.config;

import com.ttrung.supershop.order.util.CustomTokenService;
import com.ttrung.supershop.product.client.ProductService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
@EnableResourceServer
@EnableWebSecurity
public class AppConfig {
    @Value("${PRODUCT_SERVICE_BASE_URL}")
    private String productServiceBaseUrl;

    @Value("${TOKEN_CHECK_ENDPOINT}")
    private String tokenCheckEndpoint;

    @Bean
    public ProductService productClientService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(productServiceBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(ProductService.class);
    }

    @Bean
    @Primary
    public CustomTokenService tokenService(RestTemplate restTemplate) {
        CustomTokenService tokenService = new CustomTokenService();
        tokenService.setTokenCheckUri(tokenCheckEndpoint);
        tokenService.setRestTemplate(restTemplate);
        return tokenService;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}
