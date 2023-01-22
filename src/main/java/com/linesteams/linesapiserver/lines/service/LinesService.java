package com.linesteams.linesapiserver.lines.service;

import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.book.service.BookService;
import com.linesteams.linesapiserver.lines.domain.Lines;
import com.linesteams.linesapiserver.lines.dto.LinesCreateRequest;
import com.linesteams.linesapiserver.lines.dto.LinesResponse;
import com.linesteams.linesapiserver.lines.dto.LinesUpdateRequest;
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

    public LinesResponse createLines(LinesCreateRequest request) {
        Book book = bookService.getBookByIsbn(request.getIsbn())
                .orElseGet(() -> bookService.createBook(request.getBook()));

        Lines lines = linesRepository.save(request.toLines(book));
        return LinesResponse.of(lines);
    }

    @Transactional(readOnly = true)
    public Page<LinesResponse> getLinesList(Long memberId, PageRequest pageRequest) {
        return linesRepository.findAllByMemberIdAndDeleted(memberId, pageRequest, false)
                .map(LinesResponse::of);
    }

    public void createShareLog(Long linesId) {
        Lines lines = getLines(linesId);
        logService.createShareLog(lines);
    }

    private Lines getLines(Long linesId) {
        return linesRepository.findByIdAndDeleted(linesId, false)
                .orElseThrow(() -> new RuntimeException("문구를 찾을 수 없습니다."));
    }

    private Lines getMyLines(Long memberId, Long linesId) {
        return linesRepository.findByIdAndMemberIdAndDeleted(linesId, memberId, false)
                .orElseThrow(() -> new RuntimeException("문구를 찾을 수 없습니다."));
    }

    public LinesResponse updateLines(LinesUpdateRequest request) {
        Lines lines = getMyLines(request.getMemberId(), request.getId());
        lines.update(request);

        return LinesResponse.of(lines);
    }

    public void deleteLines(Long memberId, Long id) {
        Lines lines = getMyLines(memberId, id);
        lines.delete();
    }
}
