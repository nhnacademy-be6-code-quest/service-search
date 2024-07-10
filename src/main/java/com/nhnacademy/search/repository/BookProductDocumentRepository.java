package com.nhnacademy.search.repository;

import com.nhnacademy.search.document.BookProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BookProductDocumentRepository extends ElasticsearchRepository<BookProductDocument, Long> {
}
