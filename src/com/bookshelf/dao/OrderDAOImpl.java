package com.bookshelf.dao;

import com.bookshelf.entity.Book;
import com.bookshelf.entity.Order;
import com.bookshelf.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> getOrders() {
        Session session = sessionFactory.getCurrentSession();
        Query<Order> query = session.createQuery("from Order", Order.class);
        return query.getResultList();
    }

    @Override
    public void deleteOrder(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("delete from Order where orderNo=:orderNo");
        query.setParameter("orderNo", orderNo);
        query.executeUpdate();
    }
}
