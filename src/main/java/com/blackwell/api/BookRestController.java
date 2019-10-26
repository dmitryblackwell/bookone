package com.blackwell.api;


import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.repository.BookRepository;
import com.blackwell.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor
public class BookRestController {

    private BookRepository bookRepository;
    private GenreRepository genreRepository;

    @GetMapping
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/genre/{genreName}")
    public Iterable<Book> findBookByGenre(@RequestBody @PathVariable String genreName) {
        Genre genre = genreRepository.findGenreByName(genreName);
        return genre != null ?
                bookRepository.getBookByGenresContaining(genre) :
                Collections.EMPTY_LIST;
    }

}
