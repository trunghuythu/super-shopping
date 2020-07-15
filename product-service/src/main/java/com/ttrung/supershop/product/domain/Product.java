/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
package com.ttrung.supershop.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Document
@lombok.Getter
@lombok.Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    public static final String NAME_FIELD = "name";
    public static final String CATEGORY_FIELD = "category";
    public static final String BRAND_FIELD = "brand";

    @Id
    private String id;

    @Field(NAME_FIELD)
    private String name;

    @Field(CATEGORY_FIELD)
    private String category;

    @Field(BRAND_FIELD)
    private String brand;

    private Double price;
}
