package com.blackwell.service;

import com.blackwell.entity.User;

public interface UserService {
    User getUser(String username);
    void saveUser(User user);
}
