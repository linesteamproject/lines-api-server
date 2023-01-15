package com.linesteams.linesapiserver.lines.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.book.dto.BookRequest;
import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.domain.Ratio;

public class LinesCreateRequest {
    private String content;
    private BookRequest book;
    private Ratio ratio;
    private String background;
    private String font;
    private String textAlignment;

    private Long memberId;

    public LinesCreateRequest() {
    }

    public LinesCreateRequest(String content, BookRequest book, Ratio ratio, String background, String font, String textAlignment) {
        this.content = content;
        this.book = book;
        this.ratio = ratio;
        this.background = background;
        this.font = font;
        this.textAlignment = textAlignment;
    }

    public String getContent() {
        return content;
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

    @JsonIgnore
    public String getIsbn() {
        return book.getIsbn();
    }

    public Lines toLines(Book book) {
        return new Lines(ratio, background, font, textAlignment, content, memberId, book, false);
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BookRequest getBook() {
        return book;
    }
}
