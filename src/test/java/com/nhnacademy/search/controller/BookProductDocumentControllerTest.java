package com.nhnacademy.search.controller;

import com.nhnacademy.search.document.BookProductDocument;
import com.nhnacademy.search.service.BookProductDocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BookProductDocumentControllerTest {

    @Mock
    private BookProductDocumentService bookProductDocumentService;

    @InjectMocks
    private BookProductDocumentController bookProductDocumentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllBookProductDocuments() {
        // Given
        String keywords = "test";
        int page = 0;
        int size = 10;
        BookProductDocument document = new BookProductDocument(); // 필요한 필드 설정
        Page<BookProductDocument> mockPage = new PageImpl<>(Collections.singletonList(document));

        when(bookProductDocumentService.search(keywords, page, size)).thenReturn(mockPage);

        // When
        ResponseEntity<Page<BookProductDocument>> response = bookProductDocumentController.findAllBookProductDocuments(keywords, page, size);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPage, response.getBody());
        verify(bookProductDocumentService, times(1)).search(keywords, page, size);
    }
}