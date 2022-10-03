package com.linesteams.linesapiserver.book.dto;

import com.linesteams.linesapiserver.book.domain.Book;

public class BookRequest {
    private String title;
    private String name;
    private String isbn;

    public BookRequest(String title, String name, String isbn) {
        this.title = title;
        this.name = name;
        this.isbn = isbn;
    }

    public BookRequest() {
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
        return new Book(isbn, title, name);
    }
}
