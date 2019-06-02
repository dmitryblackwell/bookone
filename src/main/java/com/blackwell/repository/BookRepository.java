package com.blackwell.repository;

import com.blackwell.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    void deleteBookByIsbn(Long isbn);
}
