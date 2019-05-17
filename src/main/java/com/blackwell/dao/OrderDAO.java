package com.blackwell.dao;

import com.blackwell.entity.Order;
import com.blackwell.entity.User;

import java.util.List;

public interface OrderDAO extends DAO<Order> {
    Order get(String orderId);
    void delete(String orderNo);
}
