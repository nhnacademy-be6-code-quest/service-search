package com.nhnacademy.search.service;

import com.nhnacademy.search.document.BookProductDocument;
import com.nhnacademy.search.repository.BookProductDocumentRepository;
import com.nhnacademy.search.repository.QuerydslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookProductDocumentService {
    private final QuerydslRepository querydslRepository;
    private final BookProductDocumentRepository bookProductDocumentRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    public void productDocumentsUpdate(int size) {
        bookProductDocumentRepository.deleteAll();

        int currentPage = 0;
        Page<BookProductDocument> productDocuments;

        do {
            productDocuments = querydslRepository.findAllBookPage(null, PageRequest.of(currentPage, size, Sort.by("bookId")), null);
            bookProductDocumentRepository.saveAll(productDocuments.getContent());
            currentPage++;
        } while (currentPage < productDocuments.getTotalPages());
    }

    public Page<BookProductDocument> search(String keyword, int page, int size) {
        log.info("search keyword: " + keyword);
        Pageable pageable = PageRequest.of(page, size, Sort.by("bookId"));

        Criteria criteria = new Criteria();
        criteria = criteria.or("title").contains(keyword)
                .or("author").contains(keyword)
                .or("publisher").contains(keyword)
                .or("categorySet.categoryName").contains(keyword)
                .or("tagSet.tagName").contains(keyword);
        CriteriaQuery query = new CriteriaQuery(criteria).setPageable(pageable);

        SearchHits<BookProductDocument> searchHits = elasticsearchTemplate.search(query, BookProductDocument.class);
        List<BookProductDocument> bookProductDocuments = searchHits.getSearchHits().stream()
                .map(SearchHit::getContent)
                .toList();
        log.info("bookProductDocuments: " + bookProductDocuments);
        return new PageImpl<>(bookProductDocuments, pageable, searchHits.getTotalHits());
    }
}
