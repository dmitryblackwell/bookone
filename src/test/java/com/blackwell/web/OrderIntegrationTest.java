package com.blackwell.web;


import com.blackwell.entity.Order;
import com.blackwell.entity.OrderStatus;
import com.blackwell.util.ServiceUtils;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.blackwell.MockEntityGenerator.*;

public class OrderIntegrationTest extends IntegrationTest {

    @Test
    public void getOrdersTest() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("orderview"))
                .andExpect(model().attribute("orders", equalTo(ServiceUtils.getListFromIterable(orderRepository.findAll()))));
    }

    @Test
    public void createApproveDeleteOrderTest() throws Exception {
        userRepository.save(generateUser());
        bookRepository.save(generateBook());

        Order order = createOrderTest();
        approveOrderTest(order.getId());
        deleteOrderTest(order.getId());

        userRepository.deleteUserByUsername(USERNAME);
        bookRepository.deleteBookByIsbn(ISBN);
    }

    private Order createOrderTest() throws Exception {
        mockMvc.perform(post("/users/{username}/orders", USERNAME)
                .param("isbn", String.valueOf(ISBN)))
                .andExpect(status().isCreated());
        return getMockedOrderFromList(ServiceUtils.getListFromIterable(orderRepository.findAll()));
    }

    private void approveOrderTest(String orderNo) throws Exception {
        mockMvc.perform(post("/orders/{orderNo}", orderNo))
                .andExpect(status().isOk());
        assertEquals(orderRepository.findById(orderNo).get().getStatus(), OrderStatus.APPROVED);

    }

    private void deleteOrderTest(String orderNo) throws Exception{
        assertNotNull(orderRepository.findById(orderNo).get());

        mockMvc.perform(delete("/orders/{orderNo}", orderNo))
                .andExpect(status().isOk());
        assertNull(orderRepository.findById(orderNo).orElse(null));
    }

}
