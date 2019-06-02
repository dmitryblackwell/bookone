package com.blackwell.service.impl;

import com.blackwell.entity.User;
import com.blackwell.repository.UserRepository;
import com.blackwell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

}
