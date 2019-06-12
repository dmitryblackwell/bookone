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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	public static final int MAX_BOOKS_FOR_SLIDER = 10;

	public static final int BOOKS_ON_PAGE = 10;
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

	public static Specification<Book> getSpecificationForSearch(String textForSearch) {
        final String searchValue = StringUtils.isBlank(textForSearch) ? "%" : ("%" + textForSearch + "%");
		return (root, query, builder) -> {
			query.distinct(true);
			return builder.or(
					builder.like(root.get("name"), searchValue),
					builder.like(root.get("description"), searchValue),
					builder.like(root.join("genres").get("name"), searchValue),
					builder.like(root.join("authors").get("fullName"), searchValue)
			);
		};
	}

	@Override
	public List<BookDTO> getBooks(int pageNo, String sortColumn, String searchValue) {
	    PageRequest pageRequest = PageRequest.of(pageNo, BOOKS_ON_PAGE, Sort.by(sortColumn).descending());
		List<Book> booksIt = ServiceUtils.getListFromIterable(
		        bookRepository.findAll(getSpecificationForSearch(searchValue), pageRequest));
		return booksIt.stream()
				.map(book -> {
					Double score = commentRepository.getAvgScoreByIsbn(book.getIsbn());
					return bookConverter.convert(book).toBuilder()
							.score(score)
							.build();
				})
				.collect(Collectors.toList());
	}

	@Override
	public int getPagesCount() {
		return (int) (bookRepository.count() / BOOKS_ON_PAGE);
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
