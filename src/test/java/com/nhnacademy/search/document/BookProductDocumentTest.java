package com.nhnacademy.search.document;

import com.nhnacademy.search.entity.ProductCategory;
import com.nhnacademy.search.entity.Tag;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class BookProductDocumentTest {

    @Test
    void testNoArgsConstructor() {
        BookProductDocument document = new BookProductDocument();
        assertNotNull(document);
    }

    @Test
    void testAllArgsConstructor() {
        Set<ProductCategory> categories = new HashSet<>();
        Set<Tag> tags = new HashSet<>();

        BookProductDocument document = new BookProductDocument(
                1L, "cover.jpg", true, "Description", "Product Name", 1, 100L,
                1000L, 900L, 50L, 1L, "Book Title", "Publisher", "Author",
                "1234567890", "1234567890123", categories, tags, true
        );

        assertEquals(1L, document.getProductId());
        assertEquals("cover.jpg", document.getCover());
        assertTrue(document.isPackable());
        assertEquals("Description", document.getProductDescription());
        assertEquals("Product Name", document.getProductName());
        assertEquals(1, document.getProductState());
        assertEquals(100L, document.getProductViewCount());
        assertEquals(1000L, document.getProductPriceStandard());
        assertEquals(900L, document.getProductPriceSales());
        assertEquals(50L, document.getProductInventory());
        assertEquals(1L, document.getBookId());
        assertEquals("Book Title", document.getTitle());
        assertEquals("Publisher", document.getPublisher());
        assertEquals("Author", document.getAuthor());
        assertEquals("1234567890", document.getIsbn());
        assertEquals("1234567890123", document.getIsbn13());
        assertEquals(categories, document.getCategorySet());
        assertEquals(tags, document.getTagSet());
        assertTrue(document.isHasLike());
    }

    @Test
    void testBuilder() {
        Set<ProductCategory> categories = new HashSet<>();
        Set<Tag> tags = new HashSet<>();

        BookProductDocument document = BookProductDocument.builder()
                .productId(1L)
                .cover("cover.jpg")
                .packable(true)
                .productDescription("Description")
                .productName("Product Name")
                .productState(1)
                .productViewCount(100L)
                .productPriceStandard(1000L)
                .productPriceSales(900L)
                .productInventory(50L)
                .bookId(1L)
                .title("Book Title")
                .publisher("Publisher")
                .author("Author")
                .isbn("1234567890")
                .isbn13("1234567890123")
                .categorySet(categories)
                .tagSet(tags)
                .hasLike(true)
                .build();

        assertEquals(1L, document.getProductId());
        assertEquals("cover.jpg", document.getCover());
        assertTrue(document.isPackable());
        assertEquals("Description", document.getProductDescription());
        assertEquals("Product Name", document.getProductName());
        assertEquals(1, document.getProductState());
        assertEquals(100L, document.getProductViewCount());
        assertEquals(1000L, document.getProductPriceStandard());
        assertEquals(900L, document.getProductPriceSales());
        assertEquals(50L, document.getProductInventory());
        assertEquals(1L, document.getBookId());
        assertEquals("Book Title", document.getTitle());
        assertEquals("Publisher", document.getPublisher());
        assertEquals("Author", document.getAuthor());
        assertEquals("1234567890", document.getIsbn());
        assertEquals("1234567890123", document.getIsbn13());
        assertEquals(categories, document.getCategorySet());
        assertEquals(tags, document.getTagSet());
        assertTrue(document.isHasLike());
    }

    @Test
    void testSettersAndGetters() {
        BookProductDocument document = new BookProductDocument();

        document.setProductId(2L);
        document.setCover("new_cover.jpg");
        document.setPackable(false);
        document.setProductDescription("New Description");
        document.setProductName("New Product Name");
        document.setProductState(2);
        document.setProductViewCount(200L);
        document.setProductPriceStandard(2000L);
        document.setProductPriceSales(1800L);
        document.setProductInventory(100L);
        document.setBookId(2L);
        document.setTitle("New Book Title");
        document.setPublisher("New Publisher");
        document.setAuthor("New Author");
        document.setIsbn("0987654321");
        document.setIsbn13("0987654321098");
        Set<ProductCategory> newCategories = new HashSet<>();
        document.setCategorySet(newCategories);
        Set<Tag> newTags = new HashSet<>();
        document.setTagSet(newTags);
        document.setHasLike(false);

        assertEquals(2L, document.getProductId());
        assertEquals("new_cover.jpg", document.getCover());
        assertFalse(document.isPackable());
        assertEquals("New Description", document.getProductDescription());
        assertEquals("New Product Name", document.getProductName());
        assertEquals(2, document.getProductState());
        assertEquals(200L, document.getProductViewCount());
        assertEquals(2000L, document.getProductPriceStandard());
        assertEquals(1800L, document.getProductPriceSales());
        assertEquals(100L, document.getProductInventory());
        assertEquals(2L, document.getBookId());
        assertEquals("New Book Title", document.getTitle());
        assertEquals("New Publisher", document.getPublisher());
        assertEquals("New Author", document.getAuthor());
        assertEquals("0987654321", document.getIsbn());
        assertEquals("0987654321098", document.getIsbn13());
        assertEquals(newCategories, document.getCategorySet());
        assertEquals(newTags, document.getTagSet());
        assertFalse(document.isHasLike());
    }
}