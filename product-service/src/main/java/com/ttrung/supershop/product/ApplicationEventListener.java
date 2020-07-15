/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product;

import com.ttrung.supershop.product.dto.ProductDto;
import com.ttrung.supershop.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApplicationEventListener {

    @Autowired
    private ProductService productService;

    /**
     * Insert sample products on application startup.
     */
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        ProductDto yogurt = ProductDto.builder()
                .id("57f7d3e0-f114-4a96-89fd-8084f35c9731")
                .name("Strawberry Yogurt")
                .brand("Vinamilk")
                .price(5000d)
                .build();
        productService.createProduct(yogurt);

        ProductDto milk = ProductDto.builder()
                .id("3ebd8950-7494-4ed7-861c-04d112403332")
                .name("Dense milk")
                .brand("Vinamilk")
                .price(20000d)
                .build();
        productService.createProduct(milk);

        ProductDto xiaoMiNote5 = ProductDto.builder()
                .id("3ebd8950-7494-4ed7-861c-04d112402d32")
                .name("Xiaomi Note 5")
                .brand("Xiaomi")
                .price(2000000d)
                .build();
        productService.createProduct(xiaoMiNote5);

        ProductDto xiaomiVisionX = ProductDto.builder()
                .id("3ebd8950-7494-4ed7-861c-03k112403332")
                .name("Xiaomi Vision X")
                .brand("Xiaomi")
                .price(3000000d)
                .build();
        productService.createProduct(xiaomiVisionX);

        ProductDto samsungNote8 = ProductDto.builder()
                .id("46bd8950-7494-4ed7-861c-04d112403332")
                .name("Samsung Note 9")
                .brand("Samsung")
                .price(20000000d)
                .build();
        productService.createProduct(samsungNote8);

        ProductDto samsungGalaxy = ProductDto.builder()
                .id("3ebd8950-7494-4ed7-726d-04d112403332")
                .name("Samsung Galaxy")
                .brand("Samsung")
                .price(28000000d)
                .build();
        productService.createProduct(samsungGalaxy);

        ProductDto samsungTvUhd = ProductDto.builder()
                .id("3kjs8950-7494-4ed7-861c-04d112403332")
                .name("Samsung TV Uhd")
                .brand("Samsung")
                .price(10000000d)
                .build();
        productService.createProduct(samsungTvUhd);

        ProductDto samsungTv4k = ProductDto.builder()
                .id("3ebd8950-7494-4ed7-861c-26k112293332")
                .name("Samsung TV 4k")
                .brand("Samsung")
                .price(15000000d)
                .build();
        productService.createProduct(samsungTv4k);
    }
}
