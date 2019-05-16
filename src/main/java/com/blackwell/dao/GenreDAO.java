package com.blackwell.dao;

import java.util.List;

import com.blackwell.entity.Genre;

public interface GenreDAO {
	public List<Genre> getGenres();
	public Genre getGenre(int id);
	public Genre getGenre(String name);
}
