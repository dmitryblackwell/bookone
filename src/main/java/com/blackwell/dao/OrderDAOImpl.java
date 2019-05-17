package com.blackwell.dao;

import com.blackwell.entity.Book;
import com.blackwell.entity.Order;
import com.blackwell.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Order> get() {
        Session session = sessionFactory.getCurrentSession();
        Query<Order> query = session.createQuery("from Order", Order.class);
        return query.getResultList();
    }

    @Override
    public Order get(int id) {
        throw new NotImplementedException();
    }

    @Override
    public Order get(String orderId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Order.class, orderId);
    }

    @Override
    public void save(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(order);
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(String orderNo) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("delete from Order where orderNo=:orderNo");
        query.setParameter("orderNo", orderNo);
        query.executeUpdate();
    }
}
