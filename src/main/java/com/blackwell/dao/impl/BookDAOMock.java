package com.blackwell.dao.impl;

import com.blackwell.dao.BookDAO;
import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.util.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BookDAOMock implements BookDAO {

    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void postConstruct() {
        Genre programmingGenre = new Genre(1, "programming");
        Genre sciFiGenre = new Genre(2, "sci-fi");

        Book thinkingInJavaBook = Book.builder()
                .isbn(9780131872486L)
                .author("Bruce Eckel")
                .name("Thinking in Java")
                .price(74.2f)
                .genre(programmingGenre)
                .description("Thinking in Java should be read cover to cover" +
                        " by every Java programmer.")
                .comments(new HashSet<>())
                .build();
        Book futurologicalCongressBook = Book.builder()
                .isbn(9780826401076L)
                .author("Stanislaw Lem")
                .name("The Futurological Congress")
                .price(15.1f)
                .genre(sciFiGenre)
                .description("The Futurological Congress is a 1971 black humour " +
                        "science fiction novel.")
                .comments(new HashSet<>())
                .build();
        Book catsCradleBook = Book.builder()
                .isbn(9788071453611L)
                .author("Kurt Vonnegut")
                .name("Cats Cradle")
                .price(12f)
                .genre(sciFiGenre)
                .description("At the opening of the book, the narrator, describes " +
                        "about what important Americans did on the day Hiroshima was bombed.")
                .comments(new HashSet<>())
                .build();

        books.addAll(Arrays.asList(thinkingInJavaBook, futurologicalCongressBook, catsCradleBook));
    }

    @Override
    public List<Book> get() {
        return this.books;
    }

    @Override
    public Book get(int id) {
        return this.get((long) id);
    }

    @Override
    public Book get(long isbn) {
        return books.stream()
                .filter(book -> book.getIsbn() == isbn)
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public void save(Book book) {
        Book currentBook = (Book) CollectionUtils.find(books, o -> o.equals(book));
        if (currentBook == null) {
            books.add(book);
            return;
        }
        BeanUtils.copy(currentBook, book);
    }

    @Override
    public void delete(int id) {
        this.delete((long) id);
    }

    @Override
    public void delete(long isbn) {
        books = books.stream()
                .filter(book -> book.getIsbn() != isbn)
                .collect(Collectors.toList());
    }

}
