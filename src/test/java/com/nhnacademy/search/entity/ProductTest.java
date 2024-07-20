package com.nhnacademy.search.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testNoArgsConstructor() {
        Product product = new Product();
        assertNotNull(product);
        assertEquals("신규 상품", product.getProductName());
        assertEquals(999999999, product.getProductPriceStandard());
        assertEquals(999999999, product.getProductPriceSales());
        assertEquals(0, product.getProductViewCount());
        assertEquals(100, product.getProductInventory());
        assertEquals("상품입니다.", product.getProductDescription());
        assertNotNull(product.getProductRegisterDate());
        assertEquals(0, product.getProductState());
        assertFalse(product.isProductPackable());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDateTime now = LocalDateTime.now();
        List<ProductTag> productTags = new ArrayList<>();
        List<ProductCategoryRelation> productCategoryRelations = new ArrayList<>();
        List<ProductLike> productLikes = new ArrayList<>();

        Product product = new Product(1L, "Test Product", 1000L, 900L, 100L, 50L,
                "thumbnail.jpg", "Test Description", now, 1, true,
                productTags, productCategoryRelations, productLikes);

        assertEquals(1L, product.getProductId());
        assertEquals("Test Product", product.getProductName());
        assertEquals(1000L, product.getProductPriceStandard());
        assertEquals(900L, product.getProductPriceSales());
        assertEquals(100L, product.getProductViewCount());
        assertEquals(50L, product.getProductInventory());
        assertEquals("thumbnail.jpg", product.getProductThumbnailUrl());
        assertEquals("Test Description", product.getProductDescription());
        assertEquals(now, product.getProductRegisterDate());
        assertEquals(1, product.getProductState());
        assertTrue(product.isProductPackable());
        assertEquals(productTags, product.getProductTags());
        assertEquals(productCategoryRelations, product.getProductCategoryRelations());
        assertEquals(productLikes, product.getProductLikes());
    }

    @Test
    void testSettersAndGetters() {
        Product product = new Product();

        product.setProductId(2L);
        product.setProductName("Updated Product");
        product.setProductPriceStandard(2000L);
        product.setProductPriceSales(1800L);
        product.setProductViewCount(200L);
        product.setProductInventory(75L);
        product.setProductThumbnailUrl("new_thumbnail.jpg");
        product.setProductDescription("Updated Description");
        LocalDateTime updatedTime = LocalDateTime.now().plusDays(1);
        product.setProductRegisterDate(updatedTime);
        product.setProductState(2);
        product.setProductPackable(true);

        List<ProductTag> productTags = new ArrayList<>();
        product.setProductTags(productTags);
        List<ProductCategoryRelation> productCategoryRelations = new ArrayList<>();
        product.setProductCategoryRelations(productCategoryRelations);
        List<ProductLike> productLikes = new ArrayList<>();
        product.setProductLikes(productLikes);

        assertEquals(2L, product.getProductId());
        assertEquals("Updated Product", product.getProductName());
        assertEquals(2000L, product.getProductPriceStandard());
        assertEquals(1800L, product.getProductPriceSales());
        assertEquals(200L, product.getProductViewCount());
        assertEquals(75L, product.getProductInventory());
        assertEquals("new_thumbnail.jpg", product.getProductThumbnailUrl());
        assertEquals("Updated Description", product.getProductDescription());
        assertEquals(updatedTime, product.getProductRegisterDate());
        assertEquals(2, product.getProductState());
        assertTrue(product.isProductPackable());
        assertEquals(productTags, product.getProductTags());
        assertEquals(productCategoryRelations, product.getProductCategoryRelations());
        assertEquals(productLikes, product.getProductLikes());
    }

    @Test
    void testToString() {
        Product product = new Product();
        product.setProductId(1L);
        product.setProductName("Test Product");

        String toString = product.toString();
        assertTrue(toString.contains("productId=1"));
        assertTrue(toString.contains("productName=Test Product"));
    }
}