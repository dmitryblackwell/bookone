package com.blackwell.service.impl;

import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.repository.BookRepository;
import com.blackwell.repository.GenreRepository;
import com.blackwell.service.BookService;
import com.blackwell.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private GenreRepository genreRepository;

	
	@Override
	public Book getBook(long isbn) {return bookRepository.findById(isbn).get(); }

	@Override
	public List<Book> getBooks() {
		return ServiceUtils.getListFromIterable(bookRepository.findAll());
	}

	@Override
	public void saveBook(Book book) { bookRepository.save(book); }

	@Override
	public void deleteBook(long isbn) {	bookRepository.deleteBookByIsbn(isbn); }
	
	@Override
	public List<Genre> getGenres() {
		return ServiceUtils.getListFromIterable(genreRepository.findAll());
	}

	@Override
	public Genre getGenre(int id) {	return genreRepository.findById(id).get(); }

	@Override
	public Genre getGenre(String name) { return genreRepository.findGenreByName(name); }

}
