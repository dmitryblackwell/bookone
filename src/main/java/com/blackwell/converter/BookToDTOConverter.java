package com.blackwell.converter;

import com.blackwell.entity.Author;
import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.model.BookDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BookToDTOConverter implements Converter<Book, BookDTO> {
    @Override
    public BookDTO convert(Book book) {
        String imageUrl = StringUtils.isBlank(book.getImageUrl()) ?
                "/resources/uploaded-images/books/"+book.getIsbn()+".jpg" :
                book.getImageUrl();
        return BookDTO.builder()
                .isbn(book.getIsbn())
                .name(book.getName())
                .imageUrl(imageUrl)
                .description(book.getDescription())
                .price(book.getPrice())
                .authors(book.getAuthors().stream()
                        .map(Author::getFullName)
                        .collect(Collectors.toList()))
                .genres(book.getGenres().stream()
                        .map(Genre::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
