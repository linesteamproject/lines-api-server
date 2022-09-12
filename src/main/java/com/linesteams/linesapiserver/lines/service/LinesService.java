package com.linesteams.linesapiserver.lines.service;

import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.book.service.BookService;
import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.dto.LinesRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
import com.linesteams.linesapiserver.lines.repository.LinesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LinesService {

    private final BookService bookService;
    private final LinesRepository linesRepository;

    public LinesService(BookService bookService, LinesRepository linesRepository) {
        this.bookService = bookService;
        this.linesRepository = linesRepository;
    }

    public LinesResponse createLines(LinesRequest request) {
        Book book = bookService.getBookByIsbn(request.getIsbn())
                .orElseGet(() -> bookService.createBook(request.getBook()));

        Lines lines = linesRepository.save(request.toLines(book));
        return LinesResponse.of(lines);
    }

    public Page<LinesResponse> getLinesList(Long memberId, PageRequest pageRequest) {
        return linesRepository.findAllByMemberId(memberId, pageRequest)
                .map(LinesResponse::of);
    }
}
