package com.blackwell.web;

import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import com.blackwell.service.DAOManagerService;
import com.blackwell.util.OrderNoGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractIntegrationTest implements InitializingBean {

    static final long ISBN = 1234L;
    static final String USERNAME = "username";
    static final String COMMENT = "This is some Comment!";
    static final int QUANTITY = 2;
    private static final String NAME = "BookName";
    private static final String AUTHOR = "author";
    private static final String PASSWORD = "${bcrypt}$2y$12$A5JgjEEMpRzP14f48Qr0YesHXr7/ORtnv4jAfAiMBSIfRJf5svz5C";
    private static final String EMAIL = "username@email.com";

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
                .enabled(true)
                .password(PASSWORD)
                .comments(new HashSet<>())
                .orders(new HashSet<>())
                .build();
    }

    Book generateBook() {
        return  Book.builder()
                .isbn(ISBN)
                .name(NAME)
                .author(AUTHOR)
                .price(66.6f)
                .genre(new Genre(1, "sci-fi"))
                .comments(new HashSet<>())
                .build();
    }

    Order generateOrder(User user, Book book) {
        return Order.builder()
                .orderNo(OrderNoGenerator.generate())
                .user(user)
                .book(book)
                .quantity(QUANTITY)
                .build();
    }

    Order getMockedOrderFromList(List<Order> orders) {
        List<Order> filteredOrders = orders.stream()
                .filter(this::isOrderEqualsToMock)
                .collect(Collectors.toList());
        assertFalse(CollectionUtils.isEmpty(filteredOrders));
        return filteredOrders.get(0);
    }

    private boolean isOrderEqualsToMock(Order order) {
        return order != null && order.getBook() != null && order.getBook().getIsbn() == ISBN &&
                order.getUser() != null && StringUtils.equals(order.getUser().getUsername(), USERNAME) &&
                order.getQuantity() == QUANTITY;
    }

}
