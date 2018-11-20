package com.bookshelf.service;

import com.bookshelf.dao.OrderDAO;
import com.bookshelf.dao.UserDAO;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.Order;
import com.bookshelf.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private OrderDAO orderDAO;


    @Override
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public void addOrderForUser(String username, Book book, int quantity) {
        User user = userDAO.getUser(username);
        Order order = new Order(user, book, quantity);
        user.addOrder(order);
        saveUser(user);
    }

    @Override
    public void deleteOrder(String orderNo) {
        orderDAO.deleteOrder(orderNo);
    }
}
