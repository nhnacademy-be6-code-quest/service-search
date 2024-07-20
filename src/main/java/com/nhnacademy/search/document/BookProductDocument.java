package com.nhnacademy.search.document;

import com.nhnacademy.search.entity.ProductCategory;
import com.nhnacademy.search.entity.Tag;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "code-quest_product")
@Setting(settingPath = "elasticsearch/nori-analyzer-settings.json")
public class BookProductDocument {
    @Id
    private Long productId;

    private String cover;
    private boolean packable;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer")
    private String productDescription;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer")
    private String productName;

    private Integer productState;
    private Long productViewCount;
    private Long productPriceStandard;
    private Long productPriceSales;
    private Long productInventory;

    private Long bookId;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer")
    private String title;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer")
    private String publisher;

    @Field(type = FieldType.Text, analyzer = "nori_analyzer")
    private String author;

    private String isbn;
    private String isbn13;

    @Field(type = FieldType.Nested)
    private Set<ProductCategory> categorySet;

    @Field(type = FieldType.Nested)
    private Set<Tag> tagSet;

    private boolean hasLike;
}