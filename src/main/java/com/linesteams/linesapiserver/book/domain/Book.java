package com.linesteams.linesapiserver.book.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String isbn;

    @Column
    private String title;

    @Column
    private String name;

    public Book(String isbn, String title, String name) {
        this.isbn = isbn;
        this.title = title;
        this.name = name;
    }

    public Book() {
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
