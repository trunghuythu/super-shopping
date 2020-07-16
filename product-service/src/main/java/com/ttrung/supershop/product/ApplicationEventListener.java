/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product;

import com.ttrung.supershop.product.domain.Product;
import com.ttrung.supershop.product.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * For demo purpose, on app startup, we would insert sample products into DB.
 */
@Component
public class ApplicationEventListener {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Insert sample products on application startup.
     */
    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        Product yogurt = Product.builder()
                .id("57f7d3e0-f114-4a96-89fd-8084f35c9731")
                .name("Strawberry Yogurt")
                .category("Food")
                .brand("Vinamilk")
                .price(5000d)
                .build();
        productRepository.save(yogurt);

        Product milk = Product.builder()
                .id("3ebd8950-7494-4ed7-861c-04d112403332")
                .category("Food")
                .name("Dense milk")
                .brand("Vinamilk")
                .price(20000d)
                .build();
        productRepository.save(milk);

        Product xiaoMiNote5 = Product.builder()
                .id("3ebd8950-7494-4ed7-861c-04d112402d32")
                .category("Digital Devices")
                .name("Xiaomi Note 5")
                .brand("Xiaomi")
                .price(2000000d)
                .build();
        productRepository.save(xiaoMiNote5);

        Product xiaomiVisionX = Product.builder()
                .id("3ebd8950-7494-4ed7-861c-03k112403332")
                .category("Digital Devices")
                .name("Xiaomi Vision X")
                .brand("Xiaomi")
                .price(3000000d)
                .build();
        productRepository.save(xiaomiVisionX);

        Product samsungNote8 = Product.builder()
                .id("46bd8950-7494-4ed7-861c-04d112403332")
                .category("Digital Devices")
                .name("Samsung Note 9")
                .brand("Samsung")
                .price(20000000d)
                .build();
        productRepository.save(samsungNote8);

        Product samsungGalaxy = Product.builder()
                .id("3ebd8950-7494-4ed7-726d-04d112403332")
                .category("Digital Devices")
                .name("Samsung Galaxy")
                .brand("Samsung")
                .price(28000000d)
                .build();
        productRepository.save(samsungGalaxy);

        Product samsungTvUhd = Product.builder()
                .id("3kjs8950-7494-4ed7-861c-04d112403332")
                .category("Digital Devices")
                .name("Samsung TV Uhd")
                .brand("Samsung")
                .price(10000000d)
                .build();
        productRepository.save(samsungTvUhd);

        Product samsungTv4k = Product.builder()
                .id("3ebd8950-7494-4ed7-861c-26k112293332")
                .category("Digital Devices")
                .name("Samsung TV 4k")
                .brand("Samsung")
                .price(15000000d)
                .build();
        productRepository.save(samsungTv4k);
    }
}
