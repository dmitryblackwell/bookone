package com.bookshelf.dao;

import com.bookshelf.entity.Order;
import com.bookshelf.entity.User;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrders();
    void deleteOrder(String orderNo);

}
