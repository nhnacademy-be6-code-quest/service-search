package com.nhnacademy.search.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryRelationTest {

    private Product product;
    private ProductCategory productCategory;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1L);
        product.setProductName("Test Product");
        productCategory = ProductCategory.builder().productCategoryId(1L).categoryName("Test Category").build();
    }

    @Test
    void testNoArgsConstructor() {
        ProductCategoryRelation relation = new ProductCategoryRelation();
        assertNotNull(relation);
        assertNull(relation.getProductCategoryRelationId());
        assertNull(relation.getProduct());
        assertNull(relation.getProductCategory());
    }

    @Test
    void testAllArgsConstructor() {
        ProductCategoryRelation relation = new ProductCategoryRelation(1L, product, productCategory);

        assertEquals(1L, relation.getProductCategoryRelationId());
        assertEquals(product, relation.getProduct());
        assertEquals(productCategory, relation.getProductCategory());
    }

    @Test
    void testBuilder() {
        ProductCategoryRelation relation = ProductCategoryRelation.builder()
                .productCategoryRelationId(1L)
                .product(product)
                .productCategory(productCategory)
                .build();

        assertEquals(1L, relation.getProductCategoryRelationId());
        assertEquals(product, relation.getProduct());
        assertEquals(productCategory, relation.getProductCategory());
    }

    @Test
    void testSettersAndGetters() {
        ProductCategoryRelation relation = new ProductCategoryRelation();

        relation.setProductCategoryRelationId(2L);
        relation.setProduct(product);
        relation.setProductCategory(productCategory);

        assertEquals(2L, relation.getProductCategoryRelationId());
        assertEquals(product, relation.getProduct());
        assertEquals(productCategory, relation.getProductCategory());
    }
}