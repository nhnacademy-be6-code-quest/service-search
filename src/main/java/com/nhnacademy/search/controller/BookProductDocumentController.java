package com.nhnacademy.search.controller;

import com.nhnacademy.search.document.BookProductDocument;
import com.nhnacademy.search.service.BookProductDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookProductDocumentController {
    private final BookProductDocumentService bookProductDocumentService;

    @GetMapping("/api/search")
    public ResponseEntity<Page<BookProductDocument>> findAllBookProductDocuments(
            @RequestParam String keywords,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(bookProductDocumentService.search(keywords, page, size));
    }
}
