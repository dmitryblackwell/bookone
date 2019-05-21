package com.blackwell.dao.impl;

import com.blackwell.dao.UserDAO;
import com.blackwell.entity.Book;
import com.blackwell.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {

    private static final String HQL_GET_USERS = "from User";
    private static final String HQL_DELETE_USER = "delete from User where username=:userName";
    private final SessionFactory sessionFactory;

    @Autowired(required = false)
    public UserDAOImpl(@Nullable SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> get() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery(HQL_GET_USERS, User.class);
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

    @Override
    public void delete(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery(HQL_DELETE_USER);
        query.setParameter("userName", username);
        query.executeUpdate();
    }
}
