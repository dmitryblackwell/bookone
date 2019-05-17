package com.blackwell.dao;

import com.blackwell.entity.User;

import java.util.List;

public interface UserDAO extends DAO<User> {
    User get(String login);
}
