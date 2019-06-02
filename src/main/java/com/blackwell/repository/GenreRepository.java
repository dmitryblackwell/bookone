package com.blackwell.repository;

import com.blackwell.entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
    Genre findGenreByName(String name);
}
