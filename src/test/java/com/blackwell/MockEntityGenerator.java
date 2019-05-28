package com.blackwell;

import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import com.blackwell.util.OrderNoGenerator;

import java.util.HashSet;

public class MockEntityGenerator {

    public static final long ISBN = 1234L;
    public static final String USERNAME = "username";
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
                .enabled(true)
                .password(PASSWORD)
                .comments(new HashSet<>())
                .orders(new HashSet<>())
                .build();
    }

    public static Book generateBook() {
        return  Book.builder()
                .isbn(ISBN)
                .name(NAME)
                .author(AUTHOR)
                .price(66.6f)
                .genre(new Genre(1, "sci-fi"))
                .comments(new HashSet<>())
                .build();
    }

    public static Order generateOrder(User user, Book book) {
        return Order.builder()
                .orderNo(OrderNoGenerator.generate())
                .user(user)
                .book(book)
                .quantity(QUANTITY)
                .build();
    }

}
