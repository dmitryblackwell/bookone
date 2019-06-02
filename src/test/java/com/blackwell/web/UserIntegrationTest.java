package com.blackwell.web;

import com.blackwell.entity.Book;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import com.blackwell.util.ServiceUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.blackwell.MockEntityGenerator.*;

public class UserIntegrationTest extends IntegrationTest {

    @Test
    public void getUserTest() throws Exception {
        User user = generateUser();
        userRepository.save(user);
        mockMvc.perform(get("/users/{username}", USERNAME))
                .andExpect(status().isOk())
                .andExpect(view().name("userview"))
                .andExpect(model().attribute("user", equalTo(user)))
                .andExpect(model().attribute("orders", orderRepository.findOrdersByUserUsername(USERNAME)));
        userRepository.deleteUserByUsername(user.getUsername());
        assertNull(userRepository.findUserByUsername(user.getUsername()));
    }

    @Test
    public void saveOrderTest() throws Exception {
        User user = generateUser();
        Book book = generateBook();
        userRepository.save(user);
        bookRepository.save(book);
        mockMvc.perform(post("/users/{username}/orders", USERNAME)
                .param("isbn", String.valueOf(ISBN))
                .param("quantity", String.valueOf(QUANTITY)))
                .andExpect(status().isCreated());

        Order order = getMockedOrderFromList(ServiceUtils.getListFromIterable(orderRepository.findAll()));
        assertFalse(StringUtils.isBlank(order.getId()));
        assertNotNull(orderRepository.findById(order.getId()));

        orderRepository.deleteById(order.getId());
        userRepository.deleteUserByUsername(USERNAME);
        bookRepository.deleteBookByIsbn(ISBN);
    }

}
