package com.nhnacademy.search.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @OneToOne
    @JoinColumn(name = "productId", unique = true, nullable = false)
    private Product product;

    @Builder.Default
    @Column(nullable = false, name = "bookTitle")
    @ColumnDefault("'제목 없음'")
    private String title ="제목 없음";

    @Column(name = "bookPublisher")
    private String publisher;

    @Column(nullable = false, name = "bookAuthor")
    private String author;

    @Column(nullable = false, unique = true, length = 10, name = "bookIsbn_10")
    private String isbn;

    @Column(nullable = true, unique = true, length = 13, name = "bookIsbn_13")
    private String isbn13;

    @Column(name = "bookPubdate")
    private LocalDate pubDate;

}
