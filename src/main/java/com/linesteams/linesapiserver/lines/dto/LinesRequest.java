package com.linesteams.linesapiserver.lines.dto;

import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.domain.Ratio;

public class LinesRequest {
    public String content;
    public String isbn;
    public Ratio ratio;
    public String background;

    public LinesRequest() {
    }

    public LinesRequest(String content, String isbn, Ratio ratio, String background) {
        this.content = content;
        this.isbn = isbn;
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

    public String getIsbn() {
        return isbn;
    }

    public Lines toLines(Book book) {
        return new Lines(ratio, background, content, book);
    }
}
