package com.blackwell.service;

import java.util.List;

import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.entity.User;

public interface BookService {
	public List<Book> getBooks();
	public Book getBook(long isbn);
	public void saveBook(Book book);
	public void deleteBook(long isbn);
	
	public List<Genre> getGenres();
	public Genre getGenre(int id);
	public Genre getGenre(String name);

	void addComment(long isbn, String username, String comment);
	void deleteComment(int id);
}

// TODO make this world a better place