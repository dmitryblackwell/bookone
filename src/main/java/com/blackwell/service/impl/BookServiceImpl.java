package com.blackwell.service.impl;

import com.blackwell.converter.BookToDTOConverter;
import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.model.BookDTO;
import com.blackwell.model.ScoreDTO;
import com.blackwell.repository.BookRepository;
import com.blackwell.repository.CommentRepository;
import com.blackwell.repository.GenreRepository;
import com.blackwell.service.BookService;
import com.blackwell.util.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	public static final int MAX_BOOKS_FOR_SLIDER = 10;
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private BookToDTOConverter bookConverter;

	
	@Override
	public Book getBook(long isbn) {
		try {
			// TODO fix it later
			return bookRepository.findById(isbn).get();
		} catch (NoSuchElementException ex) {
			return null;
		}
	}

	@Override
	public List<BookDTO> getBooks() {
		return ServiceUtils.getListFromIterable(bookRepository.findAll()).stream()
				.map(book -> {
					Double score = commentRepository.getAvgScoreByIsbn(book.getIsbn());
					return bookConverter.convert(book).toBuilder()
							.score(score)
							.build();
				})
				.collect(Collectors.toList());
	}

	@Override
	public List<BookDTO> getBooksForSlider() {
		List<ScoreDTO> scoresDTOS = commentRepository.getBookAndAvgScore();
		if (scoresDTOS.size() > MAX_BOOKS_FOR_SLIDER)
			scoresDTOS = scoresDTOS.subList(0, MAX_BOOKS_FOR_SLIDER);
		return scoresDTOS.stream().map(scoreDTO -> {
			Book book = bookRepository.findById(scoreDTO.getIsbn()).get();
			return bookConverter.convert(book).toBuilder()
					.score(scoreDTO.getScore())
					.build();
		}).collect(Collectors.toList());
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
