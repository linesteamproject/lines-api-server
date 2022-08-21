package com.linesteams.linesapiserver.book.dto;

import com.linesteams.linesapiserver.book.domain.Book;

public class BookResponse {
    public String title;
    public String name;
    public String isbn;

    private BookResponse(String title, String name, String isbn) {
        this.title = title;
        this.name = name;
        this.isbn = isbn;
    }

    public BookResponse() {
    }

    public static BookResponse of(Book book) {
        if (book == null) {
            return null;
        }
        return new BookResponse(book.getTitle(), book.getAuthor(), book.getIsbn());
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
