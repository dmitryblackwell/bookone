package com.blackwell.service;

import java.util.List;

import javax.transaction.Transactional;

import com.blackwell.dao.CommentDAO;
import com.blackwell.entity.Comment;
import com.blackwell.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blackwell.dao.BookDAO;
import com.blackwell.dao.GenreDAO;
import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private CommentDAO commentDAO;
	
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

	@Override
	public void addComment(long isbn, String username, String comment) {
		commentDAO.saveComment(isbn, username, comment);
	}

	@Override
	public void deleteComment(int id) {
		commentDAO.deleteComment(id);
	}

}
