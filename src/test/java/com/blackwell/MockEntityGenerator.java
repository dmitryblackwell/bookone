package com.blackwell;

import com.blackwell.entity.*;
import com.blackwell.util.OrderNoGenerator;

import java.util.HashSet;

public class MockEntityGenerator {

    public static final long ISBN = 1234L;
    public static final String USERNAME = "username";
    public static final String FULL_NAME = "Full Name";
    public static final String COMMENT = "This is some Comment!";
    public static final int QUANTITY = 2;
    public static final String NAME = "BookName";
    public static final String AUTHOR = "author";
    public static final String PASSWORD = "${bcrypt}$2y$12$A5JgjEEMpRzP14f48Qr0YesHXr7/ORtnv4jAfAiMBSIfRJf5svz5C";
    public static final String EMAIL = "username@email.com";

    public static User generateUser() {
        return User.builder()
                .username(USERNAME)
                .email(EMAIL)
                .fullName(FULL_NAME)
                .level(UserLevel.BEGINER)
                .password(PASSWORD)
                .phoneNumber("050 1234 123")
                .enabled(true)
                .build();
    }

    public static Book generateBook() {
        Book book = Book.builder()
                .isbn(ISBN)
                .name(NAME)
                .authors(new HashSet<>())
                .price(66.6f)
                .genres(new HashSet<>())
                .build();
        book.getAuthors().add(Author.builder().id(1234).fullName(AUTHOR).build());
        book.getGenres().add(new Genre(1, "sci-fi", "Science fiction"));
        return book;
    }

    public static Order generateOrder(User user, Book book) {
        return Order.builder()
                .id(OrderNoGenerator.generate())
                .user(user)
                .book(book)
                .build();
    }

}
