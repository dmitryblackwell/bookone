package com.blackwell.api;

import com.blackwell.entity.Genre;
import com.blackwell.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genre")
public class GenreRestController {


    @Autowired
    private GenreRepository genreRepository;

    @GetMapping
    public Iterable<Genre> findAll() {
        return genreRepository.findAll();
    }

}
