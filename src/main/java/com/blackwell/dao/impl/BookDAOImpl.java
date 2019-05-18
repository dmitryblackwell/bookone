package com.blackwell.dao.impl;

import java.util.List;

import com.blackwell.dao.BookDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blackwell.entity.Book;

import javax.transaction.Transactional;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO {

	@Autowired(required = false)
	private SessionFactory sessionFactory;
	
	@Override
	public List<Book> get() {
		Session session = sessionFactory.getCurrentSession();
		Query<Book> query = session.createQuery("from Book", Book.class);
		return query.getResultList();
	}

	@Override
	public Book get(int id) {
		return this.get((long) id);
	}

	@Override
	public Book get(long isbn) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Book.class, isbn);
	}
	
	@Override
	public void save(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(book);
	}

	@Override
	public void delete(int id) {
		this.delete((long) id);
	}

	@Override
	public void delete(long isbn) {
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery("delete from Book where isbn=:bookISBN");
		query.setParameter("bookISBN", isbn);
		query.executeUpdate();		
	}

}
