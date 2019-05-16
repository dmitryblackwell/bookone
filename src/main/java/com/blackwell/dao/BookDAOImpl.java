package com.blackwell.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blackwell.entity.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Book> getBooks() {
		Session session = sessionFactory.getCurrentSession();
		Query<Book> query = session.createQuery("from Book", Book.class);
		return query.getResultList();
	}

	@Override
	public Book getBook(long isbn) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Book.class, isbn);
	}
	
	@Override
	public void saveBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(book);
	}
	
	@Override
	public void deleteBook(long isbn) {
		Session session = sessionFactory.getCurrentSession();
		Query<?> query = session.createQuery("delete from Book where isbn=:bookISBN");
		query.setParameter("bookISBN", isbn);
		query.executeUpdate();		
	}

}
