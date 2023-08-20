package com.damiane.book.booksrest.services;

import com.damiane.book.booksrest.domain.Book;

import java.util.Optional;
import java.util.List;

public interface BookService {

    boolean isBookExists(Book book);
    Book create(Book book);

    Optional<Book> findById(String isbn);

    List<Book> listBooks();

    void deleteBookById(String isbn);
}
