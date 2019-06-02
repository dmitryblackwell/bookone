package com.blackwell.service;

import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;

import java.util.List;

public interface BookService {
	List<Book> getBooks();
	Book getBook(long isbn);
	void saveBook(Book book);
	void deleteBook(long isbn);
	
	List<Genre> getGenres();
	Genre getGenre(int id);
	Genre getGenre(String name);
}

// TODO make this world a better place