package com.blackwell.web;

import com.blackwell.BookoneApplication;
import com.blackwell.config.AppConfig;
import com.blackwell.config.SecurityConfig;
import com.blackwell.config.SecurityWebApplicationInitializer;
import com.blackwell.config.SpringMvcDispatcherServletInitializer;
import com.blackwell.dao.*;
import com.blackwell.dao.impl.*;
import com.blackwell.entity.Book;
import com.blackwell.entity.Genre;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import com.blackwell.service.DAOManagerService;
import com.blackwell.util.OrderNoGenerator;
import junitparams.JUnitParamsRunner;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.model.InitializationError;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(Parameterized.class)
public abstract class IntegrationTest implements InitializingBean {

    static final long ISBN = 1234L;
    static final String USERNAME = "username";
    static final String COMMENT = "This is some Comment!";
    static final int QUANTITY = 2;
    private static final String NAME = "BookName";
    private static final String AUTHOR = "author";
    private static final String PASSWORD = "${bcrypt}$2y$12$A5JgjEEMpRzP14f48Qr0YesHXr7/ORtnv4jAfAiMBSIfRJf5svz5C";
    private static final String EMAIL = "username@email.com";

    @ClassRule
    public static final SpringClassRule SPRING_CLASS_RULE= new SpringClassRule();
    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Autowired
    DAOManagerService daoManagerService;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    UserDAO userDAO;
    BookDAO bookDAO;
    OrderDAO orderDAO;
    GenreDAO genreDAO;

    @Parameterized.Parameter
    public Class<?extends DAO> userDAOClass;

    @Parameterized.Parameter(1)
    public Class<?extends DAO> bookDAOClass;

    @Parameterized.Parameter(2)
    public Class<?extends DAO> orderDAOClass;

    @Parameterized.Parameter(3)
    public Class<?extends DAO> genreDAOClass;

    @Parameterized.Parameters
    public static Collection daoObjects() throws Exception {
        return Arrays.asList(new Object[][] {
                { UserDAOMock.class, BookDAOMock.class, OrderDAOMock.class, GenreDAOMock.class},
                { UserDAOImpl.class, BookDAOImpl.class, OrderDAOImpl.class, GenreDAOImpl.class}
        });
    }

    @Override
    public void afterPropertiesSet() {
        assertNotNull(this.wac);
        assertNotNull(this.daoManagerService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.userDAO = (UserDAO) daoManagerService.getDAO(userDAOClass);
        this.bookDAO = (BookDAO) daoManagerService.getDAO(bookDAOClass);
        this.orderDAO = (OrderDAO) daoManagerService.getDAO(orderDAOClass);
        this.genreDAO = (GenreDAO) daoManagerService.getDAO(genreDAOClass);


        assertNotNull(mockMvc);
        assertNotNull(userDAO);
        assertNotNull(bookDAO);
        assertNotNull(orderDAO);
        assertNotNull(genreDAO);
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

