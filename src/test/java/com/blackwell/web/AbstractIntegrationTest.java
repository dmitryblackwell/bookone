package com.blackwell.web;

import com.blackwell.entity.Book;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import com.blackwell.service.DAOManagerService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractIntegrationTest implements InitializingBean {

    static final long ISBN = 1234L;
    private static final String NAME = "BookName";
    static final String USERNAME = "username";
    private static final String EMAIL = "username@email.com";
    static final String COMMENT = "This is some Comment!";
    static final int QUANTITY = 2;


    @Autowired
    DAOManagerService daoManagerService;

    @Autowired
    private WebApplicationContext wac;

    MockMvc mockMvc;

    @Override
    public void afterPropertiesSet() {
        assertNotNull(this.wac);
        assertNotNull(this.daoManagerService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }


    User generateUser() {
        return User.builder()
                .username(USERNAME)
                .email(EMAIL)
                .comments(new HashSet<>())
                .orders(new HashSet<>())
                .build();
    }

    Order generateOrder(User user, Book book) {
        return Order.builder()
                .user(user)
                .book(book)
                .quantity(QUANTITY)
                .build();
    }

    Book generateBook() {
        return  Book.builder()
                .isbn(ISBN)
                .name(NAME)
                .comments(new HashSet<>())
                .build();
    }


}
