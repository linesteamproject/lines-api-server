package com.linesteams.linesapiserver.lines.dto;

import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.lines.domain.Image;
import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.domain.Ratio;
import org.springframework.web.multipart.MultipartFile;

public class LinesRequest {
    public String content;
    public String isbn;
    public Ratio ratio;
    public String background;
    public MultipartFile image;

    public LinesRequest(String content, String isbn, Ratio ratio, String background, MultipartFile image) {
        this.content = content;
        this.isbn = isbn;
        this.ratio = ratio;
        this.background = background;
        this.image = image;
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

    public MultipartFile getImage() {
        return image;
    }

    public Lines toLines(Book book, Image image) {
        return new Lines(ratio, background, content, book, image);
    }
}
