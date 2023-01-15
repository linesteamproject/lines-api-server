package com.linesteams.linesapiserver.lines.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.book.dto.BookRequest;
import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.domain.Ratio;

public class LinesCreateRequest {
    public String content;
    public BookRequest book;
    public Ratio ratio;
    public String background;
    public Long memberId;

    public LinesCreateRequest() {
    }

    public LinesCreateRequest(String content, BookRequest book, Ratio ratio, String background) {
        this.content = content;
        this.book = book;
        this.ratio = ratio;
        this.background = background;
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

    @JsonIgnore
    public String getIsbn() {
        return book.getIsbn();
    }

    public Lines toLines(Book book) {
        return new Lines(ratio, background, content, memberId, book, false);
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public BookRequest getBook() {
        return book;
    }
}
