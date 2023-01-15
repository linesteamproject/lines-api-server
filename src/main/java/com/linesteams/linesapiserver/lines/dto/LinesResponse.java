package com.linesteams.linesapiserver.lines.dto;

import com.linesteams.linesapiserver.book.dto.BookResponse;
import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.domain.Ratio;

public class LinesResponse {
    public Long id;
    public Ratio ratio;
    public String background;
    public String font;
    public String textAlignment;
    public String content;
    public BookResponse bookResponse;

    public LinesResponse(Long id, Ratio ratio, String background, String font, String textAlignment, String content, BookResponse bookResponse) {
        this.id = id;
        this.ratio = ratio;
        this.background = background;
        this.font = font;
        this.textAlignment = textAlignment;
        this.content = content;
        this.bookResponse = bookResponse;
    }

    public LinesResponse() {
    }

    public static LinesResponse of(Lines lines) {
        return new LinesResponse(lines.getId(), lines.getRatio(), lines.getBackground(), lines.getFont(), lines.getTextAlignment(), lines.getContent(), BookResponse.of(lines.getBook()));
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

    public BookResponse getBookResponse() {
        return bookResponse;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setRatio(Ratio ratio) {
        this.ratio = ratio;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setBookResponse(BookResponse bookResponse) {
        this.bookResponse = bookResponse;
    }
}
