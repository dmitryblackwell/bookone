package com.bookshelf.service;

import com.bookshelf.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();
    Order getOrder(String orderId);
    void saveOrder(Order order);
    void approve(String orderNo);
    void deleteOrder(String orderNo);
}
