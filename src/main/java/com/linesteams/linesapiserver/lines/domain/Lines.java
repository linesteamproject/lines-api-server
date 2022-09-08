package com.linesteams.linesapiserver.lines.domain;

import com.linesteams.linesapiserver.book.domain.Book;

import javax.persistence.Column;
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

    @Column
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    public Lines(Ratio ratio, String background, String content, Long memberId, Book book) {
        this.ratio = ratio;
        this.background = background;
        this.content = content;
        this.memberId = memberId;
        this.book = book;
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
}
