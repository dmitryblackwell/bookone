package com.blackwell.repository;

import com.blackwell.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
    List<Order> findOrdersByUserUsername(String username);
}
