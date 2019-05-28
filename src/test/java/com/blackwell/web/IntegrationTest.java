package com.blackwell.web;

import com.blackwell.dao.BookDAO;
import com.blackwell.dao.GenreDAO;
import com.blackwell.dao.OrderDAO;
import com.blackwell.dao.UserDAO;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static com.blackwell.MockEntityGenerator.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class IntegrationTest implements InitializingBean {



    @Autowired
    DAOManagerService daoManagerService;

    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    UserDAO userDAO;
    BookDAO bookDAO;
    OrderDAO orderDAO;
    GenreDAO genreDAO;

    @Override
    public void afterPropertiesSet() {
        assertNotNull(this.wac);
        assertNotNull(this.daoManagerService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        this.userDAO = daoManagerService.getDAO(UserDAO.class);
        this.bookDAO = daoManagerService.getDAO(BookDAO.class);
        this.orderDAO = daoManagerService.getDAO(OrderDAO.class);
        this.genreDAO = daoManagerService.getDAO(GenreDAO.class);


        assertNotNull(mockMvc);
        assertNotNull(userDAO);
        assertNotNull(bookDAO);
        assertNotNull(orderDAO);
        assertNotNull(genreDAO);
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

