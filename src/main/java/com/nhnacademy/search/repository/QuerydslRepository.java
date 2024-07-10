package com.nhnacademy.search.repository;

import com.nhnacademy.search.document.BookProductDocument;
import com.nhnacademy.search.entity.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.nhnacademy.search.entity.QProduct.product;
import static com.nhnacademy.search.entity.QProductTag.productTag;
import static com.nhnacademy.search.entity.QTag.tag;
import static com.nhnacademy.search.entity.QProductCategoryRelation.productCategoryRelation;
import static com.nhnacademy.search.entity.QProductCategory.productCategory;
import static com.nhnacademy.search.entity.QProductLike.productLike;
import static com.querydsl.core.types.dsl.Wildcard.count;


@Slf4j
@Repository
@Transactional(readOnly = true)
public class QuerydslRepository extends QuerydslRepositorySupport {

    private final QProduct p = new QProduct("product");
    private final QBook b = new QBook("book");

    public QuerydslRepository() {
        super(Product.class);
    }

    private JPQLQuery<Tuple> baseQuery(){
        return from(b)
                .select(b.bookId, b.title, b.publisher, b.author, b.isbn, b.isbn13, b.pubDate,
                        b.product, p.productId, p.productThumbnailUrl, p.productName, p.productPackable,
                        p.productDescription, p.productRegisterDate, p.productState, p.productViewCount,
                        p.productPriceStandard, p.productPriceSales, p.productInventory)
                .distinct()
                .innerJoin(b.product, p);
    }

    private JPQLQuery<Long> countQuery(){
        return from(b)
                .select(count)
                .innerJoin(b.product, p);
    }

    private OrderSpecifier<?> makeOrderSpecifier(Pageable pageable, String entity) {
        Sort sort = pageable.getSort();
        Sort.Order order = sort.iterator().next();
        String property = order.getProperty();
        Order orderDirect = order.isDescending()? Order.DESC : Order.ASC;

        return new OrderSpecifier<>(orderDirect, Expressions.stringTemplate(entity + "." + property));
    }

    private BookProductDocument makeBookProductGetResponseDto(Tuple tuple, boolean hasProductLike) {
        return BookProductDocument.builder()
                .bookId(tuple.get(b.bookId))
                .title(tuple.get(b.title))
                .publisher(tuple.get(b.publisher))
                .author(tuple.get(b.author))
                .isbn(tuple.get(b.isbn))
                .isbn13(tuple.get(b.isbn13))
                .productId(tuple.get(p.productId))
                .cover(tuple.get(p.productThumbnailUrl))
                .productName(tuple.get(p.productName))
                .packable(tuple.get(p.productPackable))
                .productDescription(tuple.get(p.productDescription))
                .productState(tuple.get(p.productState))
                .productViewCount(tuple.get(p.productViewCount))
                .productPriceStandard(tuple.get(p.productPriceStandard))
                .productPriceSales(tuple.get(p.productPriceSales))
                .productInventory(tuple.get(p.productInventory))
                .categorySet(getCategorySet(tuple.get(b.product)))
                .tagSet(getTagSet(tuple.get(b.product)))
                .hasLike(hasProductLike)
                .build();
    }

    private boolean hasProductLike(Long clientId, Long productId) {
        log.info("checking productLike client : {}. book : {}", clientId, productId);
        if (clientId != null && clientId != 1){
            Long count = countQuery()
                    .innerJoin(p.productLikes, productLike)
                    .where(p.productId.eq(productId).and(productLike.clientId.eq(clientId)))
                    .fetchOne();
            return count > 0;
        }
        return false;
    }

    private Page<BookProductDocument> makePage(JPQLQuery<Tuple> query, JPQLQuery<Long> countQuery , Pageable pageable, Long clientId){
        List<Tuple> tupleList = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<BookProductDocument> result = new ArrayList<>();
        for (Tuple tuple : tupleList) {
            result.add(makeBookProductGetResponseDto(tuple, hasProductLike(clientId, tuple.get(p.productId))));
        }

        long totalPages = countQuery.fetchOne();
        return new PageImpl<>(result, pageable, totalPages);
    }
    
    public Page<BookProductDocument> findAllBookPage(Long clientId, Pageable pageable, Integer productState){
        OrderSpecifier<?> orderSpecifier = makeOrderSpecifier(pageable, "book");
        BooleanBuilder whereBuilder = new BooleanBuilder();

        if(productState != null) {
            whereBuilder.and(product.productState.eq(productState));
        }

        JPQLQuery<Tuple> query = baseQuery()
                .where(whereBuilder)
                .orderBy(orderSpecifier);

        JPQLQuery<Long> countQuery = countQuery()
                .where(whereBuilder);

        return makePage(query, countQuery, pageable, clientId);
    }
    
    public Set<ProductCategory> getCategorySet(Product realProduct) {
        return Set.copyOf(
                from(p)
                        .select(productCategory)
                        .distinct()
                        .innerJoin(p.productCategoryRelations, productCategoryRelation)
                        .innerJoin(productCategoryRelation.productCategory, productCategory)
                        .where(p.eq(realProduct))
                        .fetch());
    }
    
    public Set<Tag> getTagSet(Product realProduct){
        return Set.copyOf(
                from(p)
                .select(tag)
                .distinct()
                .innerJoin(p.productTags, productTag)
                .innerJoin(productTag.tag, tag)
                .where(p.eq(realProduct))
                .fetch());
    }
}
