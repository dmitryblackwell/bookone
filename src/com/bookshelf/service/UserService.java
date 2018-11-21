package com.bookshelf.service;

import com.bookshelf.entity.Book;
import com.bookshelf.entity.Order;
import com.bookshelf.entity.User;

import java.util.List;

public interface UserService {
    User getUser(String login);
    void saveUser(User user);
    void addOrderForUser(String username, Book book, int quantity);
}
