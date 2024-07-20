package com.nhnacademy.search.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductLikeTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1L);
        product.setProductName("Test Product");
    }

    @Test
    void testNoArgsConstructor() {
        ProductLike productLike = new ProductLike();
        assertNotNull(productLike);
        assertNull(productLike.getProductLikeId());
        assertNull(productLike.getClientId());
        assertNull(productLike.getProduct());
    }

    @Test
    void testAllArgsConstructor() {
        ProductLike productLike = new ProductLike(1L, 100L, product);

        assertEquals(1L, productLike.getProductLikeId());
        assertEquals(100L, productLike.getClientId());
        assertEquals(product, productLike.getProduct());
    }

    @Test
    void testBuilder() {
        ProductLike productLike = ProductLike.builder()
                .productLikeId(1L)
                .clientId(100L)
                .product(product)
                .build();

        assertEquals(1L, productLike.getProductLikeId());
        assertEquals(100L, productLike.getClientId());
        assertEquals(product, productLike.getProduct());
    }

    @Test
    void testSettersAndGetters() {
        ProductLike productLike = new ProductLike();

        productLike.setProductLikeId(2L);
        productLike.setClientId(200L);
        productLike.setProduct(product);

        assertEquals(2L, productLike.getProductLikeId());
        assertEquals(200L, productLike.getClientId());
        assertEquals(product, productLike.getProduct());
    }
}