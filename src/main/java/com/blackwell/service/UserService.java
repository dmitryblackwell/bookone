package com.blackwell.service;

import com.blackwell.entity.Book;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;

import java.util.List;

public interface UserService {
    User getUser(String login);
    void saveUser(User user);
    void addOrderForUser(String username, Book book, int quantity);
}
