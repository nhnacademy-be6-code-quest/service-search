package com.nhnacademy.search.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryTest {

    @Test
    void testNoArgsConstructor() {
        ProductCategory category = new ProductCategory();
        assertNotNull(category);
        assertNull(category.getProductCategoryId());
        assertNull(category.getCategoryName());
        assertNull(category.getParentProductCategory());
    }

    @Test
    void testAllArgsConstructor() {
        ProductCategory parentCategory = new ProductCategory(1L, "Parent Category", null);
        ProductCategory category = new ProductCategory(2L, "Test Category", parentCategory);

        assertEquals(2L, category.getProductCategoryId());
        assertEquals("Test Category", category.getCategoryName());
        assertEquals(parentCategory, category.getParentProductCategory());
    }

    @Test
    void testBuilder() {
        ProductCategory parentCategory = ProductCategory.builder()
                .productCategoryId(1L)
                .categoryName("Parent Category")
                .build();

        ProductCategory category = ProductCategory.builder()
                .productCategoryId(2L)
                .categoryName("Test Category")
                .parentProductCategory(parentCategory)
                .build();

        assertEquals(2L, category.getProductCategoryId());
        assertEquals("Test Category", category.getCategoryName());
        assertEquals(parentCategory, category.getParentProductCategory());
    }

    @Test
    void testSettersAndGetters() {
        ProductCategory category = new ProductCategory();

        category.setProductCategoryId(3L);
        category.setCategoryName("Updated Category");
        ProductCategory parentCategory = new ProductCategory(4L, "New Parent Category", null);
        category.setParentProductCategory(parentCategory);

        assertEquals(3L, category.getProductCategoryId());
        assertEquals("Updated Category", category.getCategoryName());
        assertEquals(parentCategory, category.getParentProductCategory());
    }

    @Test
    void testParentCategoryNull() {
        ProductCategory category = ProductCategory.builder()
                .productCategoryId(5L)
                .categoryName("Root Category")
                .build();

        assertNull(category.getParentProductCategory());
    }
}