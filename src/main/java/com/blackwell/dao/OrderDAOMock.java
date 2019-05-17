package com.blackwell.dao;

import com.blackwell.entity.Order;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderDAOMock implements OrderDAO {

    private List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> get() {
        return orders;
    }

    @Override
    public Order get(int id) {
        throw new NotImplementedException();
    }

    @Override
    public Order get(String orderId) {
        return orders.stream()
                .filter(order -> StringUtils.equals(order.getOrderNo(), orderId))
                .collect(Collectors.toList())
                .get(0);
    }

    @Override
    public void save(Order order) {
        orders.add(order);
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(String orderNo) {
        orders = orders.stream()
                .filter(order -> !StringUtils.equals(order.getOrderNo(), orderNo))
                .collect(Collectors.toList());
    }
}
