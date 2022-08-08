package com.linesteams.linesapiserver.lines.service;

import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.book.service.BookService;
import com.linesteams.linesapiserver.lines.domain.Image;
import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.dto.LinesRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
import com.linesteams.linesapiserver.lines.repository.LinesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LinesService {

    private final BookService bookService;
    private final ImageService imageService;
    private final LinesRepository linesRepository;

    public LinesService(BookService bookService, ImageService imageService, LinesRepository linesRepository) {
        this.bookService = bookService;
        this.imageService = imageService;
        this.linesRepository = linesRepository;
    }

    public LinesResponse createLines(LinesRequest request) {
        Book book = bookService.getBookByIsbn(request.getIsbn())
                .orElseGet(() -> bookService.createBook(request.getIsbn()));

        Image image = imageService.createImage(request.getImage());

        Lines lines = linesRepository.save(request.toLines(book, image));
        return LinesResponse.of(lines);
    }
}
