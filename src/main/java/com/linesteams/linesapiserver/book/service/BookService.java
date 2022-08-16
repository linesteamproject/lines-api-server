package com.linesteams.linesapiserver.book.service;

import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.book.domain.BookRepository;
import com.linesteams.linesapiserver.book.dto.BookInfoItemDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final NaverService naverService;

    public BookService(BookRepository bookRepository, NaverService naverService) {
        this.bookRepository = bookRepository;
        this.naverService = naverService;
    }

    @Transactional(readOnly = true)
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book createBook(String isbn) {
        BookInfoItemDto bookInfoItemDto = naverService.getBookByIsbn(isbn);
        if (bookInfoItemDto == null) {
            return null;
        }

        return bookRepository.save(Book.of(bookInfoItemDto));
    }
}
