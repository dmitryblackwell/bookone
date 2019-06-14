package com.blackwell.service.impl;

import com.blackwell.converter.BookToDTOConverter;
import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.model.BookDTO;
import com.blackwell.model.BookFilter;
import com.blackwell.model.ScoreDTO;
import com.blackwell.repository.BookRepository;
import com.blackwell.repository.CommentRepository;
import com.blackwell.repository.GenreRepository;
import com.blackwell.service.BookService;
import com.blackwell.util.ServiceUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
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
        final String searchValue = StringUtils.contains(textForSearch,"%") ? textForSearch :
				StringUtils.isBlank(textForSearch) ? "%" : ("%" + textForSearch + "%");

		return (root, query, builder) -> {
			query.distinct(true);
			return builder.or(
					builder.like(root.get("name"), searchValue),
					builder.like(root.get("description"), searchValue),
					builder.like(root.join("genres").get("name"), searchValue),
					builder.like(root.join("authors").get("fullName").as(String.class), searchValue)
			);
		};
	}

	public static Specification<Book> getFilterSpecification(BookFilter filter) {
		return (root, query, builder) -> {
			Predicate predicate = getSpecificationForSearch(filter.getSearchValue())
					.toPredicate(root, query, builder);

			for (String name : CollectionUtils.emptyIfNull(filter.getGenresNames())) {
				predicate = builder.and(predicate,
						builder.like(root.join("genres").get("name"), name));
			}

			return predicate;
		};
	}


	@Override
	public List<BookDTO> getBooks(BookFilter filter) {
		Specification<Book> specification = getFilterSpecification(filter);

	    PageRequest pageRequest = PageRequest.of(filter.getPageNo(), BOOKS_ON_PAGE, Sort.by(filter.getSortColumn()).descending());
		List<Book> booksIt = ServiceUtils.getListFromIterable(
		        bookRepository.findAll(specification, pageRequest));
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
		int pagesCount = (int) (bookRepository.count() / BOOKS_ON_PAGE);
		if (bookRepository.count()%BOOKS_ON_PAGE == 0)
			pagesCount--;
		return pagesCount;
	}

	@Override
	@Deprecated
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
