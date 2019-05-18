package com.blackwell.dao.impl;

import com.blackwell.dao.OrderDAO;
import com.blackwell.dao.UserDAO;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import com.blackwell.util.OrderNoGenerator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class OrderDAOMock implements OrderDAO {

    private final UserDAO userDAO;

    @Autowired
    public OrderDAOMock(@Qualifier("userDAOMock") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<Order> get() {
        List<Order> orders = new ArrayList<>();
        userDAO.get().forEach(user -> orders.addAll(user.getOrders()));
        return orders;
    }

    @Override
    public Order get(int id) {
        throw new NotImplementedException();
    }

    @Override
    public Order get(String orderId) {
        List<Order> filteredOrders = get().stream()
                .filter(order -> StringUtils.equals(order.getOrderNo(), orderId))
                .collect(Collectors.toList());

        return CollectionUtils.isEmpty(filteredOrders) ? null : filteredOrders.get(0);
    }

    @Override
    public void save(Order order) {
        if (get().contains(order))
            return;
        order.setOrderNo(OrderNoGenerator.generate());
        userDAO.get(order.getUser().getUsername())
                .getOrders().add(order);
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(String orderNo) {
        for(User user : userDAO.get()) {
            Set<Order> filteredOrders = user.getOrders().stream()
                    .filter(order -> !StringUtils.equals(order.getOrderNo(), orderNo))
                    .collect(Collectors.toSet());
            user.setOrders(filteredOrders);
        }
    }
}
