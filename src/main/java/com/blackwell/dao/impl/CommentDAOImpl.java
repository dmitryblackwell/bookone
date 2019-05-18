package com.blackwell.dao.impl;

import com.blackwell.dao.CommentDAO;
import com.blackwell.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.loader.custom.sql.SQLQueryParser;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommentDAOImpl implements CommentDAO {

    private static final String HQL_INSERT_INTO_COMMENTS = "INSERT INTO comments VALUES (default, :isbn, :username, :comment);";
    private static final String HQL_DELETE_COMMENT = "delete from Comment where id=:commentId";

    private final SessionFactory sessionFactory;

    @Autowired(required = false)
    public CommentDAOImpl(@Nullable SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(long isbn, String username, String comment) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(HQL_INSERT_INTO_COMMENTS);
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
        Query<?> query = session.createQuery(HQL_DELETE_COMMENT);
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
