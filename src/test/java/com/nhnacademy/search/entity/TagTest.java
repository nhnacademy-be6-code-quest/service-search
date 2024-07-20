package com.nhnacademy.search.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    @Test
    void testBuilderAndGetters() {
        // Given
        Long tagId = 1L;
        String tagName = "Java";

        // When
        Tag tag = Tag.builder()
                .tagId(tagId)
                .tagName(tagName)
                .build();

        // Then
        assertNotNull(tag);
        assertEquals(tagId, tag.getTagId());
        assertEquals(tagName, tag.getTagName());
    }

    @Test
    void testNoArgsConstructor() {
        // When
        Tag tag = new Tag();

        // Then
        assertNotNull(tag);
        assertNull(tag.getTagId());
        assertNull(tag.getTagName());
    }

    @Test
    void testAllArgsConstructor() {
        // Given
        Long tagId = 2L;
        String tagName = "Spring";

        // When
        Tag tag = new Tag(tagId, tagName);

        // Then
        assertNotNull(tag);
        assertEquals(tagId, tag.getTagId());
        assertEquals(tagName, tag.getTagName());
    }

    @Test
    void testSetters() {
        // Given
        Tag tag = new Tag();
        Long tagId = 3L;
        String tagName = "JPA";

        // When
        tag.setTagId(tagId);
        tag.setTagName(tagName);

        // Then
        assertEquals(tagId, tag.getTagId());
        assertEquals(tagName, tag.getTagName());
    }
}