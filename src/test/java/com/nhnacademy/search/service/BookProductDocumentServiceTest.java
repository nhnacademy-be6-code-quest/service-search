package com.nhnacademy.search.service;

import com.nhnacademy.search.document.BookProductDocument;
import com.nhnacademy.search.repository.BookProductDocumentRepository;
import com.nhnacademy.search.repository.QuerydslRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookProductDocumentServiceTest {

    @Mock
    private QuerydslRepository querydslRepository;

    @Mock
    private BookProductDocumentRepository bookProductDocumentRepository;

    @InjectMocks
    private BookProductDocumentService bookProductDocumentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProductDocumentsUpdate() {
        int size = 10;
        Page<BookProductDocument> mockPage = new PageImpl<>(List.of(new BookProductDocument()), PageRequest.of(0, size), 15);

        when(querydslRepository.findAllBookPage(eq(null), any(PageRequest.class), eq(null)))
                .thenReturn(mockPage)
                .thenReturn(new PageImpl<>(List.of(), PageRequest.of(1, size), 15));

        bookProductDocumentService.productDocumentsUpdate(size);

        verify(bookProductDocumentRepository, times(1)).deleteAll();
        verify(bookProductDocumentRepository, times(2)).saveAll(anyList());
        verify(querydslRepository, times(2)).findAllBookPage(eq(null), any(PageRequest.class), eq(null));
    }

    @Test
    void testSearch() {
        String keyword = "test";
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "_score"));
        Page<BookProductDocument> mockPage = new PageImpl<>(List.of(new BookProductDocument()), pageRequest, 1);

        when(bookProductDocumentRepository.searchByKeyword(keyword, pageRequest)).thenReturn(mockPage);

        Page<BookProductDocument> result = bookProductDocumentService.search(keyword, page, size);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
        assertEquals(1, result.getContent().size());

        verify(bookProductDocumentRepository, times(1)).searchByKeyword(keyword, pageRequest);
    }
}