package com.bookshelf.dao;

import com.bookshelf.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    User getUser(String login);
}
