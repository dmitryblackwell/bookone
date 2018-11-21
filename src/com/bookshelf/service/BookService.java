package com.bookshelf.service;

import java.util.List;

import com.bookshelf.entity.Book;
import com.bookshelf.entity.Genre;
import com.bookshelf.entity.User;

public interface BookService {
	public List<Book> getBooks();
	public Book getBook(long isbn);
	public void saveBook(Book book);
	public void deleteBook(long isbn);
	
	public List<Genre> getGenres();
	public Genre getGenre(int id);
	public Genre getGenre(String name);

	void addComment(Book book, User ser, String comment);
}

// TODO make this world a better place