package com.linesteams.linesapiserver.lines.dto;

import com.linesteams.linesapiserver.book.dto.BookResponse;
import com.linesteams.linesapiserver.lines.domain.Image;
import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.domain.Ratio;

public class LinesResponse {
    private final Long id;
    private final Ratio ratio;
    private final String background;
    private final String content;
    private final BookResponse bookResponse;
    private final Image image;

    public LinesResponse(Long id, Ratio ratio, String background, String content, BookResponse bookResponse, Image image) {
        this.id = id;
        this.ratio = ratio;
        this.background = background;
        this.content = content;
        this.bookResponse = bookResponse;
        this.image = image;
    }

    public static LinesResponse of(Lines lines) {
        return new LinesResponse(lines.getId(), lines.getRatio(), lines.getBackground(), lines.getContent(),
                BookResponse.of(lines.getBook()),
                lines.getImage());
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

    public BookResponse getBookResponse() {
        return bookResponse;
    }

    public Image getImage() {
        return image;
    }
}
