package com.linesteams.linesapiserver.book.domain;

import com.linesteams.linesapiserver.book.dto.BookInfoItemDto;
import com.linesteams.linesapiserver.config.jpa.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(32)")
    private String isbn;

    @Column(columnDefinition = "varchar(64)")
    private String title;

    @Column(columnDefinition = "varchar(64)")
    private String author;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public static Book of(BookInfoItemDto bookInfoItemDto) {
        return new Book(bookInfoItemDto.getIsbn(), bookInfoItemDto.getTitle(), bookInfoItemDto.getAuthor());
    }
}
