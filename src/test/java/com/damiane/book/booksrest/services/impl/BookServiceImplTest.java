package com.damiane.book.booksrest.services.impl;


import com.damiane.book.booksrest.domain.Book;
import com.damiane.book.booksrest.domain.BookEntity;
import com.damiane.book.booksrest.repositories.BookRepository;
import com.damiane.book.booksrest.services.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl underTest;

    @Test
    public void testThatBookIsSaved() {

        final Book book = Book.builder().isbn(isbn: "0232323").author(author: "Virginia").title(title: "The wawse").build();

        final BookEntity bookEntity = BookEntity.builder().isbn(isbn: "0232323").author(author: "Virginia").title(title: "The wawse").build();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);

        final Book result = underTest.create(book);
        assertEquals(book, result);


    }
}
