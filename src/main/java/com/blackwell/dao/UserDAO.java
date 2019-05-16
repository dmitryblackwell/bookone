package com.blackwell.dao;

import com.blackwell.entity.User;

import java.util.List;

public interface UserDAO {
    List<User> getUsers();
    User getUser(String login);
    void saveUser(User user);
}
