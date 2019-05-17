package com.blackwell.dao;

import com.blackwell.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(long isbn, String username, String comment) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery("INSERT INTO comments VALUES (default, :isbn, :username, :comment);");
        query.setParameter("isbn", isbn);
        query.setParameter("username", username);
        query.setParameter("comment", comment);
        query.executeUpdate();
    }

    @Override
    public void save(Comment comment) {
        this.save(comment.getBook().getIsbn(),
                  comment.getUser().getUsername(),
                  comment.getComment());
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<?> query = session.createQuery("delete from Comment where id=:commentId");
        query.setParameter("commentId", id);
        query.executeUpdate();
    }

    @Override
    public List<Comment> get() {
        throw new NotImplementedException();
    }

    @Override
    public Comment get(int id) {
        throw new NotImplementedException();
    }
}
