package com.blackwell.web;

import com.blackwell.dao.BookDAO;
import com.blackwell.dao.OrderDAO;
import com.blackwell.dao.UserDAO;
import com.blackwell.entity.Book;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserIntegrationTest extends AbstractIntegrationTest {

    private UserDAO userDAO;

    private OrderDAO orderDAO;

    private BookDAO bookDAO;

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        this.userDAO = daoManagerService.getDAO(UserDAO.class);
        this.orderDAO = daoManagerService.getDAO(OrderDAO.class);
        this.bookDAO= daoManagerService.getDAO(BookDAO.class);
    }


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
        Order generateOrder = generateOrder(user, book);
        userDAO.save(user);
        bookDAO.save(book);
        mockMvc.perform(post("/users/{username}/orders", USERNAME)
                .param("isbn", String.valueOf(ISBN))
                .param("quantity", String.valueOf(QUANTITY)))
                .andExpect(status().isCreated());

        List<Order> filteredOrders = orderDAO.get().stream()
                                    .filter(this::isOrderEqualsToMock)
                                    .collect(Collectors.toList());
        assertFalse(CollectionUtils.isEmpty(filteredOrders));
        Order order = filteredOrders.get(0);
        assertFalse(StringUtils.isBlank(order.getOrderNo()));
        assertNotNull(orderDAO.get(order.getOrderNo()));

        orderDAO.delete(order.getOrderNo());
        userDAO.delete(USERNAME);
        bookDAO.delete(ISBN);
    }

    private boolean isOrderEqualsToMock(Order order) {
        return order != null && order.getBook() != null && order.getBook().getIsbn() == ISBN &&
                order.getUser() != null && StringUtils.equals(order.getUser().getUsername(), USERNAME) &&
                order.getQuantity() == QUANTITY;
    }



}
