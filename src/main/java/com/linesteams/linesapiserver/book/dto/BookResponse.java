package com.linesteams.linesapiserver.book.dto;

import com.linesteams.linesapiserver.book.domain.Book;

public class BookResponse {
    private final String title;
    private final String name;
    private final String isbn;

    private BookResponse(String title, String name, String isbn) {
        this.title = title;
        this.name = name;
        this.isbn = isbn;
    }

    public static BookResponse of(Book book) {
        return new BookResponse(book.getTitle(), book.getName(), book.getIsbn());
    }
}
