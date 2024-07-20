package com.nhnacademy.search.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryId;

    @Column(nullable = false, unique = true, name = "categoryName")
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "parentCategoryId")
    @ColumnDefault("null")
    private ProductCategory parentProductCategory;
}
