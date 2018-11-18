package com.bookshelf.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshelf.dao.BookDAO;
import com.bookshelf.dao.GenreDAO;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.Genre;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private GenreDAO genreDAO;
	
	@Override
	public Book getBook(long isbn) {return bookDAO.getBook(isbn); }

	@Override
	public List<Book> getBooks() { return bookDAO.getBooks(); }

	@Override
	public void saveBook(Book book) { bookDAO.saveBook(book); }

	@Override
	public void deleteBook(long isbn) {	bookDAO.deleteBook(isbn); }
	
	@Override
	public List<Genre> getGenres() { return genreDAO.getGenres(); }

	@Override
	public Genre getGenre(int id) {	return genreDAO.getGenre(id); }

	@Override
	public Genre getGenre(String name) { return genreDAO.getGenre(name); }



}
