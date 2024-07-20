package com.nhnacademy.search.repository;

import com.nhnacademy.search.document.BookProductDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookProductDocumentRepository extends ElasticsearchRepository<BookProductDocument, Long> {
    //@Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"productName^3\", \"productDescription\", \"title^2\", \"author\"], \"type\": \"best_fields\"}}")
    @Query("{\"bool\": {\"should\": [" +
            "{\"match\": {\"productName\": {\"query\": \"?0\", \"boost\": 3}}}," +
            "{\"match\": {\"productDescription\": {\"query\": \"?0\", \"boost\": 1}}}," +
            "{\"match\": {\"title\": {\"query\": \"?0\", \"boost\": 2}}}," +
            "{\"match\": {\"author\": {\"query\": \"?0\", \"boost\": 1.5}}}," +
            "{\"match\": {\"publisher\": {\"query\": \"?0\", \"boost\": 1}}}" +
            "]}}")
    Page<BookProductDocument> searchByKeyword(String keyword, Pageable pageable);
}
