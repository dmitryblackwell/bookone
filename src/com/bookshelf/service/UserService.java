package com.bookshelf.service;

import com.bookshelf.entity.User;

public interface UserService {
    User getUser(String login);
}
