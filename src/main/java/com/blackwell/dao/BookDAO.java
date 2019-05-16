package com.blackwell.dao;

import java.util.List;

import com.blackwell.entity.Book;

public interface BookDAO {
	public List<Book> getBooks();
	public Book getBook(long isbn);
	public void saveBook(Book book);
	public void deleteBook(long isbn);
}
