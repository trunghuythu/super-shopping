
package com.ttrung.supershop.product.controller;

import com.ttrung.supershop.product.dto.PriceCalculationResult;
import com.ttrung.supershop.product.dto.ProductDto;
import com.ttrung.supershop.product.dto.ProductOrderDto;
import com.ttrung.supershop.product.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductDto>> getProducts(@RequestParam("$filter") String filter,
                                                        @RequestParam("$sort") String sort,
                                                        @RequestParam("$page") int page,
                                                        @RequestParam("$size") int size) {
        Page<ProductDto> products = productService.getProducts(filter, sort, page, size);

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {
        Optional<ProductDto> product = productService.getProductById(productId);

        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //TODO: @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productForm) {
        ProductDto createdProduct = productService.createProduct(productForm);
        return ResponseEntity.ok(createdProduct);
    }

    //TODO: @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable String productId, @Valid @RequestBody ProductDto productForm) {
        ProductDto createdProduct = productService.updateProduct(productId, productForm);
        return ResponseEntity.ok(createdProduct);
    }

    @PostMapping("/price-calculation")
    public ResponseEntity<PriceCalculationResult> calculateTotalPrice(@RequestBody Collection<ProductOrderDto> productOrders) {
        return ResponseEntity.ok(productService.calculateTotalPrice(productOrders));
    }
}
