package com.blackwell.dao;

import com.blackwell.entity.Book;
import com.blackwell.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<User> get() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User get(int id) {
        throw new NotImplementedException();
    }

    @Override
    public User get(String login) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, login);
    }

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().saveOrUpdate(user);
    }

    @Override
    public void delete(int id) {
        throw new NotImplementedException();
    }
}
