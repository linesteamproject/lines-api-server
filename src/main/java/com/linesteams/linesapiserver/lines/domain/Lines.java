package com.linesteams.linesapiserver.lines.domain;

import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.config.jpa.BaseTimeEntity;
import com.linesteams.linesapiserver.lines.dto.LinesUpdateRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "`lines`")
public class Lines extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(16)")
    @Enumerated(EnumType.STRING)
    private Ratio ratio;

    @Column(columnDefinition = "varchar(16)")
    private String background;

    @Column(columnDefinition = "varchar(16)")
    private String font;

    @Column(columnDefinition = "varchar(16)")
    private String textAlignment;

    @Column(columnDefinition = "varchar(128)")
    private String content;

    @Column(columnDefinition = "int")
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(columnDefinition = "boolean")
    private boolean deleted;

    public Lines(Ratio ratio, String background, String font, String textAlignment, String content, Long memberId, Book book, boolean deleted) {
        this.ratio = ratio;
        this.background = background;
        this.font = font;
        this.textAlignment = textAlignment;
        this.content = content;
        this.memberId = memberId;
        this.book = book;
        this.deleted = deleted;
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

    public String getFont() {
        return font;
    }

    public String getTextAlignment() {
        return textAlignment;
    }

    public String getContent() {
        return content;
    }

    public Book getBook() {
        return book;
    }

    public void update(LinesUpdateRequest request) {
        this.ratio = request.getRatio();
        this.background = request.getBackground();
        this.content = request.getContent();
        this.font = request.getFont();
        this.textAlignment = request.getTextAlignment();
    }

    public void delete() {
        this.deleted = true;
    }
}
