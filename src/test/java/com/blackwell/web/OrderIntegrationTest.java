package com.blackwell.web;


import com.blackwell.dao.BookDAO;
import com.blackwell.dao.OrderDAO;
import com.blackwell.dao.UserDAO;
import com.blackwell.entity.Order;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
public class OrderIntegrationTest extends AbstractIntegrationTest {

    private OrderDAO orderDAO;

    private BookDAO bookDAO;

    private UserDAO userDAO;

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        this.orderDAO = daoManagerService.getDAO(OrderDAO.class);
        this.bookDAO = daoManagerService.getDAO(BookDAO.class);
        this.userDAO = daoManagerService.getDAO(UserDAO.class);
    }

    @Test
    public void getOrdersTest() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderview"))
                .andExpect(model().attribute("orders", equalTo(orderDAO.get())));
    }

    @Test
    public void createApproveDeleteOrderTest() throws Exception {
        userDAO.save(generateUser());
        bookDAO.save(generateBook());

        Order order = createOrderTest();
        approveOrderTest(order.getOrderNo());
        deleteOrderTest(order.getOrderNo());

        userDAO.delete(USERNAME);
        bookDAO.delete(ISBN);
    }

    private Order createOrderTest() throws Exception {
        mockMvc.perform(post("/users/{username}/orders", USERNAME)
                .param("isbn", String.valueOf(ISBN))
                .param("quantity", String.valueOf(QUANTITY)))
                .andExpect(status().isCreated());
        return getMockedOrderFromList(orderDAO.get());
    }

    private void approveOrderTest(String orderNo) throws Exception {
        mockMvc.perform(post("/orders/{orderNo}", orderNo))
                .andExpect(status().isOk());
        assertEquals(orderDAO.get(orderNo).getStatus(), 1);
    }

    private void deleteOrderTest(String orderNo) throws Exception{
        assertNotNull(orderDAO.get(orderNo));
        mockMvc.perform(delete("/orders/{orderNo}", orderNo))
                .andExpect(status().isOk());
        assertNull(orderDAO.get(orderNo));
    }

}
