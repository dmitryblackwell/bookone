package com.blackwell.repository;

import com.blackwell.entity.Book;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;


@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    void deleteBookByIsbn(Long isbn);

    Iterable<Book> findAll(Specification<Book> searchValue, Pageable pageable);

}
