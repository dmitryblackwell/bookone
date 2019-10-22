package com.blackwell.api;


import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.repository.BookRepository;
import com.blackwell.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// I am fucking drunk, bitches!

@RestController
@RequestMapping("/api/book/")
@AllArgsConstructor // Yeah, Bitch, I can do IT~!
public class BookRestController {

    private BookRepository bookRepository;
    private GenreRepository genreRepository;

    @GetMapping
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("genre/{genreName}")
    public Iterable<Book> findBookByGenre(@RequestBody @PathVariable String genreName) {
        Genre genre = genreRepository.findGenreByName(genreName);
        return bookRepository.getBookByGenresContaining(genre);
    }

}
