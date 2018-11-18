package com.bookshelf.dao;

import java.util.List;

import com.bookshelf.entity.Genre;

public interface GenreDAO {
	public List<Genre> getGenres();
	public Genre getGenre(int id);
	public Genre getGenre(String name);
}
