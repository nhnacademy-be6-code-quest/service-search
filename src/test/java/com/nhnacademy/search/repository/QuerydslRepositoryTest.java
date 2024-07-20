package com.nhnacademy.search.repository;

import com.nhnacademy.search.document.BookProductDocument;
import com.nhnacademy.search.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class QuerydslRepositoryTest {

    @InjectMocks
    private QuerydslRepository querydslRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllBookPage() {
        // Given
        Long clientId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Integer productState = 1;

        BookProductDocument mockDocument = createMockBookProductDocument();
        List<BookProductDocument> mockList = Arrays.asList(mockDocument);
        Page<BookProductDocument> mockPage = new PageImpl<>(mockList, pageable, 1);

        // QuerydslRepository를 부분 모킹
        QuerydslRepository spyRepository = spy(querydslRepository);
        doReturn(mockPage).when(spyRepository).findAllBookPage(clientId, pageable, productState);

        // When
        Page<BookProductDocument> result = spyRepository.findAllBookPage(clientId, pageable, productState);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(mockDocument, result.getContent().get(0));

        // 메소드가 한 번 호출되었는지 확인
        verify(spyRepository, times(1)).findAllBookPage(clientId, pageable, productState);
    }

    @Test
    void testGetCategorySet() {
        // Given
        Product mockProduct = new Product();
        Set<ProductCategory> mockCategories = new HashSet<>(Arrays.asList(new ProductCategory()));

        // QuerydslRepository를 부분 모킹
        QuerydslRepository spyRepository = spy(querydslRepository);
        doReturn(mockCategories).when(spyRepository).getCategorySet(mockProduct);

        // When
        Set<ProductCategory> result = spyRepository.getCategorySet(mockProduct);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());

        // 메소드가 한 번 호출되었는지 확인
        verify(spyRepository, times(1)).getCategorySet(mockProduct);
    }

    @Test
    void testGetTagSet() {
        // Given
        Product mockProduct = new Product();
        Set<Tag> mockTags = new HashSet<>(Arrays.asList(new Tag()));

        // QuerydslRepository를 부분 모킹
        QuerydslRepository spyRepository = spy(querydslRepository);
        doReturn(mockTags).when(spyRepository).getTagSet(mockProduct);

        // When
        Set<Tag> result = spyRepository.getTagSet(mockProduct);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());

        // 메소드가 한 번 호출되었는지 확인
        verify(spyRepository, times(1)).getTagSet(mockProduct);
    }

    private BookProductDocument createMockBookProductDocument() {
        return BookProductDocument.builder()
                .bookId(1L)
                .title("Test Book")
                .publisher("Test Publisher")
                .author("Test Author")
                .isbn("1234567890")
                .isbn13("1234567890123")
                .productId(1L)
                .cover("http://example.com/cover.jpg")
                .productName("Test Product")
                .packable(true)
                .productDescription("Test Description")
                .productState(1)
                .productViewCount(100L)
                .productPriceStandard(20000L)
                .productPriceSales(18000L)
                .productInventory(50L)
                .categorySet(new HashSet<>())
                .tagSet(new HashSet<>())
                .hasLike(false)
                .build();
    }
}