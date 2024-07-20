package com.nhnacademy.search.service;

import com.nhnacademy.search.document.BookProductDocument;
import com.nhnacademy.search.repository.BookProductDocumentRepository;
import com.nhnacademy.search.repository.QuerydslRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class BookProductDocumentService {
    private final QuerydslRepository querydslRepository;
    private final BookProductDocumentRepository bookProductDocumentRepository;

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
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "_score"));
        return bookProductDocumentRepository.searchByKeyword(keyword, pageRequest);
    }
}
