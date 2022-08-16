package com.linesteams.linesapiserver.book.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookInfoItemDto {
    private String title;
    private String link;
    private String image;
    private String author;
    private String publisher;
    private String isbn;

    public BookInfoItemDto(String title, String link, String image, String author, String publisher, String isbn) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}
