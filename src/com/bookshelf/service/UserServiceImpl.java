package com.bookshelf.service;

import com.bookshelf.dao.UserDAO;
import com.bookshelf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUser(String login) {
        return userDAO.getUser(login);
    }
}
