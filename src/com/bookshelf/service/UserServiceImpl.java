package com.bookshelf.service;

import com.bookshelf.dao.OrderDAO;
import com.bookshelf.dao.UserDAO;
import com.bookshelf.entity.Book;
import com.bookshelf.entity.Order;
import com.bookshelf.entity.User;
import com.bookshelf.util.MailUtil;
import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDAO userDAO;

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
        try {
            new MailUtil().sendMail(user.getEmail(), "Your order", "Your order with book " +
                    book.getName() + " is confirmed!");
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (EmailException e) {
            LOGGER.error(e);
        }
    }
}
