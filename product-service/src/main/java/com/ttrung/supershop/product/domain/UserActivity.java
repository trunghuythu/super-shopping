
package com.ttrung.supershop.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;

@Document
@Builder
public class UserActivity {

    @Id
    private String id;
    private String userId;
    private String productId;
    private ActivityType actType;
    private String filter;
    private String sortBy;
}
