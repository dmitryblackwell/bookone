package com.blackwell.dao;

import com.blackwell.entity.Order;
import com.blackwell.entity.User;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrders();
    Order getOrder(String orderId);
    void saveOrder(Order order);
    void deleteOrder(String orderNo);
}
