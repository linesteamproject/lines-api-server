package com.linesteams.linesapiserver.book.dto;

import com.linesteams.linesapiserver.book.domain.Book;

public class BookRequest {
    private final String title;
    private final String name;
    private final String isbn;

    public BookRequest(String title, String name, String isbn) {
        this.title = title;
        this.name = name;
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book toBook() {
        return new Book(title, name, isbn);
    }
}
