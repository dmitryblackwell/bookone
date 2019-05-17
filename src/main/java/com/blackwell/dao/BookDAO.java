package com.blackwell.dao;

import java.util.List;

import com.blackwell.entity.Book;

public interface BookDAO extends DAO<Book> {
	Book get(long isbn);
	void delete(long isbn);
}
