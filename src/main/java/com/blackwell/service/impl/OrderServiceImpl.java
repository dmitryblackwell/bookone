package com.blackwell.service.impl;

import com.blackwell.dao.OrderDAO;
import com.blackwell.entity.Order;
import com.blackwell.service.DAOManagerService;
import com.blackwell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private DAOManagerService daoManagerService;

    private OrderDAO orderDAO;

    @PostConstruct
    public void postConstruct() {
        orderDAO = daoManagerService.getDAO(OrderDAO.class);
    }

    @Override
    public List<Order> getOrders() {
        return orderDAO.get();
    }

    @Override
    public Order getOrder(String orderId) {
        return orderDAO.get(orderId);
    }

    @Override
    public void saveOrder(Order order) {
        orderDAO.save(order);
    }

    @Override
    public void approve(String orderNo) {
        Order order = orderDAO.get(orderNo);
        order.setStatus(1);
        orderDAO.save(order);
    }

    @Override
    public void deleteOrder(String orderNo) {
        orderDAO.delete(orderNo);
    }

}
