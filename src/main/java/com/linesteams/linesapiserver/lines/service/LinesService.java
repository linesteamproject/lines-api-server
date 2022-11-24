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
    private final LogService logService;
    private final LinesRepository linesRepository;

    public LinesService(BookService bookService, LogService logService, LinesRepository linesRepository) {
        this.bookService = bookService;
        this.logService = logService;
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

    public void createShareLog(Long linesId) {
        Lines lines = getLines(linesId);
        logService.createShareLog(lines);
    }

    private Lines getLines(Long linesId) {
        return linesRepository.findById(linesId)
                .orElseThrow(() -> new RuntimeException("문구를 찾을 수 없습니다."));
    }
}
