package com.nhnacademy.search.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategoryRelation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryRelationId;

    @JoinColumn(name = "productId", nullable = false)
    @ManyToOne
    private Product product;

    @JoinColumn(name = "productCategoryId", nullable = false)
    @ManyToOne
    private ProductCategory productCategory;
}
