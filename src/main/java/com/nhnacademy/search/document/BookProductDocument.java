package com.nhnacademy.search.document;

import com.nhnacademy.search.entity.ProductCategory;
import com.nhnacademy.search.entity.Tag;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "code-quest_product")
public class BookProductDocument {
    @Id
    private Long productId;
    private String cover;
    private boolean packable;
    private String productDescription;
    private String productName;
    private Integer productState;
    private Long productViewCount;
    private Long productPriceStandard;
    private Long productPriceSales;
    private Long productInventory;

    private Long bookId;
    private String title;
    private String publisher;
    private String author;
    private String isbn;
    private String isbn13;

    private Set<ProductCategory> categorySet;
    private Set<Tag> tagSet;
    private boolean hasLike;
}
