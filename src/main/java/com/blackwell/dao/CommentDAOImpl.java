package com.blackwell.dao;

import com.blackwell.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveComment(long isbn, String username, String comment) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("INSERT INTO comments VALUES (default, :isbn, :username, :comment);");
        query.setParameter("isbn", isbn);
        query.setParameter("username", username);
        query.setParameter("comment", comment);
        query.executeUpdate();
    }

    @Override
    public void deleteComment(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("delete from Comment where id=:commentId");
        query.setParameter("commentId", id);
        query.executeUpdate();
    }
}
