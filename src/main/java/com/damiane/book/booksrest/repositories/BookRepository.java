package com.damiane.book.booksrest.repositories;

import com.damiane.book.booksrest.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<BookEntity, String> {

}
