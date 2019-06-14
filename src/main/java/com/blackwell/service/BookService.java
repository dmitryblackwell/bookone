package com.blackwell.service;

import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.model.BookDTO;
import com.blackwell.model.BookFilter;

import java.util.List;

public interface BookService {
	List<BookDTO> getBooks(BookFilter filter);
	List<BookDTO> getBooksForSlider();
	Book getBook(long isbn);
	void saveBook(Book book);
	void deleteBook(long isbn);
	int getPagesCount();
	
	List<Genre> getGenres();
	Genre getGenre(int id);
	Genre getGenre(String name);
}

// TODO make this world a better place