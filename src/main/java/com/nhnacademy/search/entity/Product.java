package com.nhnacademy.search.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    @ColumnDefault("'신규 상품'")
    private String productName = "신규 상품";

    @Column(nullable = false)
    @ColumnDefault("999999999")
    private long productPriceStandard = 999999999;

    @Column(nullable = false)
    @ColumnDefault("999999999")
    private long productPriceSales = 999999999;

    @Column(nullable = false)
    @ColumnDefault("0")
    private long productViewCount = 0;

    @Column(nullable = false)
    @ColumnDefault("100")
    private long productInventory = 100;

    @Column(nullable = false)
    private String productThumbnailUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String productDescription = "상품입니다.";

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime productRegisterDate = LocalDateTime.now();

    @Column(nullable = false, columnDefinition = "TINYINT")
    @ColumnDefault("0")
    private int productState = 0;

    @Column(nullable = false, name = "productPackable")
    @ColumnDefault("false")
    private boolean productPackable = false;

    @OneToMany(mappedBy = "product")
    private List<ProductTag> productTags;

    @OneToMany(mappedBy = "product")
    private List<ProductCategoryRelation> productCategoryRelations;

    @OneToMany(mappedBy = "product")
    private List<ProductLike> productLikes;
}
