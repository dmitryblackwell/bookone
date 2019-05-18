package com.blackwell.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import com.blackwell.dao.CommentDAO;
import com.blackwell.entity.Comment;
import com.blackwell.entity.User;
import com.blackwell.service.BookService;
import com.blackwell.service.DAOManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.blackwell.dao.BookDAO;
import com.blackwell.dao.GenreDAO;
import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;

@Service
public class BookServiceImpl implements BookService {

	private final DAOManagerService daoManagerService;

	private BookDAO bookDAO;

	private CommentDAO commentDAO;

	private GenreDAO genreDAO;

	@Autowired
	public BookServiceImpl(DAOManagerService daoManagerService) {
		this.daoManagerService = daoManagerService;
	}

	@PostConstruct
	public void postConstruct() {
		bookDAO = daoManagerService.getDAO(BookDAO.class);
		commentDAO = daoManagerService.getDAO(CommentDAO.class);
		genreDAO = daoManagerService.getDAO(GenreDAO.class);
	}
	
	@Override
	public Book getBook(long isbn) {return bookDAO.get(isbn); }

	@Override
	public List<Book> getBooks() { return bookDAO.get(); }

	@Override
	public void saveBook(Book book) { bookDAO.save(book); }

	@Override
	public void deleteBook(long isbn) {	bookDAO.delete(isbn); }
	
	@Override
	public List<Genre> getGenres() { return genreDAO.get(); }

	@Override
	public Genre getGenre(int id) {	return genreDAO.get(id); }

	@Override
	public Genre getGenre(String name) { return genreDAO.get(name); }

	@Override
	public void addComment(long isbn, String username, String comment) {
		commentDAO.save(isbn, username, comment);
	}

	@Override
	public void deleteComment(int id) {
		commentDAO.delete(id);
	}

}
