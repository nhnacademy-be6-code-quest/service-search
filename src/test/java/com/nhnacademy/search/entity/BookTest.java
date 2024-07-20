package com.nhnacademy.search.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1L);
    }

    @Test
    void testNoArgsConstructor() {
        Book book = new Book();
        assertNotNull(book);
        assertEquals("제목 없음", book.getTitle());
    }

    @Test
    void testAllArgsConstructor() {
        LocalDate pubDate = LocalDate.of(2023, 7, 15);
        Book book = new Book(1L, product, "Test Book", "Test Publisher", "Test Author", "1234567890", "1234567890123", pubDate);

        assertEquals(1L, book.getBookId());
        assertEquals(product, book.getProduct());
        assertEquals("Test Book", book.getTitle());
        assertEquals("Test Publisher", book.getPublisher());
        assertEquals("Test Author", book.getAuthor());
        assertEquals("1234567890", book.getIsbn());
        assertEquals("1234567890123", book.getIsbn13());
        assertEquals(pubDate, book.getPubDate());
    }

    @Test
    void testBuilder() {
        LocalDate pubDate = LocalDate.of(2023, 7, 15);
        Book book = Book.builder()
                .bookId(1L)
                .product(product)
                .title("Test Book")
                .publisher("Test Publisher")
                .author("Test Author")
                .isbn("1234567890")
                .isbn13("1234567890123")
                .pubDate(pubDate)
                .build();

        assertEquals(1L, book.getBookId());
        assertEquals(product, book.getProduct());
        assertEquals("Test Book", book.getTitle());
        assertEquals("Test Publisher", book.getPublisher());
        assertEquals("Test Author", book.getAuthor());
        assertEquals("1234567890", book.getIsbn());
        assertEquals("1234567890123", book.getIsbn13());
        assertEquals(pubDate, book.getPubDate());
    }

    @Test
    void testSettersAndGetters() {
        Book book = new Book();

        book.setBookId(2L);
        book.setProduct(product);
        book.setTitle("Updated Book");
        book.setPublisher("Updated Publisher");
        book.setAuthor("Updated Author");
        book.setIsbn("0987654321");
        book.setIsbn13("0987654321098");
        LocalDate updatedPubDate = LocalDate.of(2023, 7, 16);
        book.setPubDate(updatedPubDate);

        assertEquals(2L, book.getBookId());
        assertEquals(product, book.getProduct());
        assertEquals("Updated Book", book.getTitle());
        assertEquals("Updated Publisher", book.getPublisher());
        assertEquals("Updated Author", book.getAuthor());
        assertEquals("0987654321", book.getIsbn());
        assertEquals("0987654321098", book.getIsbn13());
        assertEquals(updatedPubDate, book.getPubDate());
    }

    @Test
    void testBuilderDefaultValue() {
        Book book = Book.builder().build();
        assertEquals("제목 없음", book.getTitle());
    }
}