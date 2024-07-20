package com.nhnacademy.search.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTagTest {

    private Product product;
    private Tag tag;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1L);
        product.setProductName("Test Product");
        tag = Tag.builder().tagId(1L).tagName("Test Tag").build();
    }

    @Test
    void testConstructorAndGetters() {
        ProductTag productTag = new ProductTag(1L, product, tag);

        assertEquals(1L, productTag.getProductTagId());
        assertEquals(product, productTag.getProduct());
        assertEquals(tag, productTag.getTag());
    }

    @Test
    void testNoArgsConstructor() {
        ProductTag productTag = new ProductTag();

        assertNull(productTag.getProductTagId());
        assertNull(productTag.getProduct());
        assertNull(productTag.getTag());
    }

    @Test
    void testSetters() {
        ProductTag productTag = new ProductTag();

        productTag.setProductTagId(2L);
        productTag.setProduct(product);
        productTag.setTag(tag);

        assertEquals(2L, productTag.getProductTagId());
        assertEquals(product, productTag.getProduct());
        assertEquals(tag, productTag.getTag());
    }

    @Test
    void testBuilder() {
        ProductTag productTag = ProductTag.builder()
                .productTagId(3L)
                .product(product)
                .tag(tag)
                .build();

        assertEquals(3L, productTag.getProductTagId());
        assertEquals(product, productTag.getProduct());
        assertEquals(tag, productTag.getTag());
    }
}
