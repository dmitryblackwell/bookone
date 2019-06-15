package com.blackwell.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    private long isbn;
    private String name;
    private float price;
    private String description;
    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Genre> genres;
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Author> authors;

    public List<String> getAuthorsNames() {
        if (authors == null)
            return Collections.emptyList();
        return authors.stream().map(Author::getFullName).collect(Collectors.toList());
    }

    public List<String> getGenresNames() {
        return genres.stream().map(Genre::getName).collect(Collectors.toList());
    }
}
