package com.bookshelf.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookshelf.entity.Genre;

@Repository
public class GenreDAOImpl implements GenreDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Genre> getGenres() {
		Session session = sessionFactory.getCurrentSession();
		Query<Genre> query = session.createQuery("from Genre", Genre.class);
		return query.getResultList();
	}

	
	@Override
	public Genre getGenre(int id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Genre.class, id);
	}


	@Override
	public Genre getGenre(String name) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Genre.class); // TODO change this "code"
		
	    criteria.add(Restrictions.eq("name", name));
		return (Genre) criteria.uniqueResult();
	}
	
	
}
