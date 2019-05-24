package com.blackwell.web;

import com.blackwell.BookoneApplication;
import com.blackwell.dao.BookDAO;
import com.blackwell.dao.DAO;
import com.blackwell.dao.OrderDAO;
import com.blackwell.dao.UserDAO;
import com.blackwell.dao.impl.*;
import com.blackwell.entity.Book;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(UserController.class)
public class UserIntegrationTest extends IntegrationTest {

    @Test
    public void getUserTest() throws Exception {
        User user = generateUser();
        userDAO.save(user);
        mockMvc.perform(get("/users/{username}", USERNAME))
                .andExpect(status().isOk())
                .andExpect(view().name("userview"))
                .andExpect(model().attribute("user", equalTo(user)))
                .andExpect(model().attribute("orders", user.getOrders()));
        userDAO.delete(user.getUsername());
        assertNull(userDAO.get(user.getUsername()));
    }

    @Test
    public void saveOrderTest() throws Exception {
        User user = generateUser();
        Book book = generateBook();
        userDAO.save(user);
        bookDAO.save(book);
        mockMvc.perform(post("/users/{username}/orders", USERNAME)
                .param("isbn", String.valueOf(ISBN))
                .param("quantity", String.valueOf(QUANTITY)))
                .andExpect(status().isCreated());

        Order order = getMockedOrderFromList(orderDAO.get());
        assertFalse(StringUtils.isBlank(order.getOrderNo()));
        assertNotNull(orderDAO.get(order.getOrderNo()));

        orderDAO.delete(order.getOrderNo());
        userDAO.delete(USERNAME);
        bookDAO.delete(ISBN);
    }

}
