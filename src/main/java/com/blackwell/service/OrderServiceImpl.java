package com.blackwell.service;

import com.blackwell.dao.OrderDAO;
import com.blackwell.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public List<Order> getOrders() {
        return orderDAO.getOrders();
    }

    @Override
    public Order getOrder(String orderId) {
        return orderDAO.getOrder(orderId);
    }

    @Override
    public void saveOrder(Order order) {
        orderDAO.saveOrder(order);
    }

    @Override
    public void approve(String orderNo) {
        Order order = orderDAO.getOrder(orderNo);
        order.setStatus(1);
        orderDAO.saveOrder(order);
    }

    @Override
    public void deleteOrder(String orderNo) {
        orderDAO.deleteOrder(orderNo);
    }

}
