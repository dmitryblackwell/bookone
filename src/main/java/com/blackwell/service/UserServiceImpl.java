package com.blackwell.service;

import com.blackwell.dao.OrderDAO;
import com.blackwell.dao.UserDAO;
import com.blackwell.entity.Book;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import org.apache.log4j.Logger;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private DAOManagerService daoManagerService;

    private UserDAO userDAO;

    @PostConstruct
    public void postConstruct() {
        userDAO = daoManagerService.getDAO(UserDAO.class);
    }

    @Override
    public User getUser(String login) {
        return userDAO.get(login);
    }

    @Override
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Override
    public void addOrderForUser(String username, Book book, int quantity) {
        User user = userDAO.get(username);
        Order order = Order.builder()
                .user(user)
                .book(book)
                .quantity(quantity)
                .build();
        user.getOrders().add(order);
        saveUser(user);
    }
}
