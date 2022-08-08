package com.linesteams.linesapiserver.lines.domain;

import com.linesteams.linesapiserver.book.domain.Book;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lines")
public class Lines {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Ratio ratio;

    @Column
    private String background;

    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Embedded
    private Image image;

    public Lines(Ratio ratio, String background, String content, Book book, Image image) {
        this.ratio = ratio;
        this.background = background;
        this.content = content;
        this.book = book;
        this.image = image;
    }

    public Lines() {
    }

    public Long getId() {
        return id;
    }

    public Ratio getRatio() {
        return ratio;
    }

    public String getBackground() {
        return background;
    }

    public String getContent() {
        return content;
    }

    public Book getBook() {
        return book;
    }

    public Image getImage() {
        return image;
    }
}
