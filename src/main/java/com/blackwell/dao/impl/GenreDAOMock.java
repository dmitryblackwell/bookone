package com.blackwell.dao.impl;

import com.blackwell.dao.GenreDAO;
import com.blackwell.entity.Genre;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GenreDAOMock implements GenreDAO {

    private List<Genre> genres = new ArrayList<>();

    @PostConstruct
    public void postConstruct() {
        Genre programmingGenre = new Genre(1, "programming");
        Genre sciFiGenre = new Genre(2, "sci-fi");
        genres.addAll(Arrays.asList(programmingGenre, sciFiGenre));
    }

    @Override
    public List<Genre> get() {
        return this.genres;
    }

    @Override
    public Genre get(int id) {
        return this.genres.get(id);
    }

    @Override
    public Genre get(String name) {
        List<Genre> filteredGenres = genres.stream()
                .filter(genre -> StringUtils.equals(genre.getName(), name))
                .collect(Collectors.toList());

        return CollectionUtils.isEmpty(filteredGenres) ? null : filteredGenres.get(0);
    }

    @Override
    public void save(Genre genre) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException();
    }

}
