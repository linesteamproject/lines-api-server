package com.linesteams.linesapiserver.book.service;

import com.linesteams.linesapiserver.book.domain.Book;
import com.linesteams.linesapiserver.book.domain.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public Book createBook(String isbn) {
        // TODO ISBN을 가지고 조회하는 api필요.
        // 해당 Api를 통해 처리해야함
        return new Book();
    }
}
