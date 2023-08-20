package com.damiane.book.booksrest.services;

import com.damiane.book.booksrest.domain.Book;
import com.damiane.book.booksrest.domain.BookEntity;
import com.damiane.book.booksrest.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.swing.text.html.Option;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {



    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean isBookExists(Book book) {
        return bookRepository.existsById(book.getIsbn());
    }

    @Override
    public Book create(final Book book) {
        final BookEntity bookEntity = bookToBookEntity(book);
        final BookEntity savedBookEntity = bookRepository.save(bookEntity);
        return bookEntityToBook(savedBookEntity);

    }



    private BookEntity bookToBookEntity(Book book) {
        return BookEntity.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }

    private Book bookEntityToBook(BookEntity bookEntity) {
        return Book.builder()
                .isbn(bookEntity.getIsbn())
                .title(bookEntity.getTitle())
                .author(bookEntity.getAuthor())
                .build();
    }

    @Override
    public Optional<Book> findById(String isbn) {
        final Optional<BookEntity> foundBook = bookRepository.findById(isbn);
        return foundBook.map(book -> bookEntityToBook(book));
    }

    @Override
    public List<Book> listBooks() {
        final List<BookEntity> foundBooks = bookRepository.findAll();
        return foundBooks.stream().map(book -> bookEntityToBook(book)).collect(Collectors.toList());
    }

    @Override
    public void deleteBookById(String isbn) {
        try {
            bookRepository.deleteById(isbn);

        } catch (final EmptyResultDataAccessException ex) {
            System.out.println("Attempted to delete non-existn book" + ex);
        }
    }


}
