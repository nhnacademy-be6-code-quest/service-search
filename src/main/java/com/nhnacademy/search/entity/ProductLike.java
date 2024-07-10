package com.nhnacademy.search.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_like", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"clientId", "productId"})
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productLikeId;

    private Long clientId;

    @JoinColumn(name = "productId", nullable = false)
    @ManyToOne
    private Product product;
}
