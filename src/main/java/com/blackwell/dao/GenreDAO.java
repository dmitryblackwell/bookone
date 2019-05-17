package com.blackwell.dao;

import java.util.List;

import com.blackwell.entity.Genre;

public interface GenreDAO extends DAO<Genre> {
	public Genre get(String name);
}
