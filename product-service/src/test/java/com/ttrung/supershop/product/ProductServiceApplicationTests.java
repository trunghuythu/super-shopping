package com.ttrung.supershop.product;

import com.ttrung.supershop.product.dto.ProductDto;
import com.ttrung.supershop.product.repository.ProductRepository;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests implements WithAssertions {

    private static final String PRODUCT_API_BASE_CONTEXT = "/v1/products";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void userShouldBeAbleToCreateProduct() {
        ProductDto product = this.restTemplate.getForObject("http://localhost:" + port + PRODUCT_API_BASE_CONTEXT + "/c7a98989-07e0-47bb-98b0-e1df0cf4b133",
                ProductDto.class);
        assertThat(product).isNotNull();
    }

}
