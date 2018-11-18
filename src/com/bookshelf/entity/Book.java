package com.bookshelf.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="books")
public class Book {
	
	@Id
	@Column(name="isbn")
	private long isbn;
	
	@Column(name="author")
	private String author;
	
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private float price;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name="genre")
	private Genre genre;
	
	@Column(name="description")
	private String description;
	
	public Book() {}	
	public Book(long isbn, String author, String name, float price, Genre genre) {
		super();
		this.isbn = isbn;
		this.author = author;
		this.name = name;
		this.price = price;
		this.genre = genre;
	}
	
	public long getIsbn() {
		return isbn;
	}
	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", author=" + author + ", name=" + name + ", genre=" + genre + "]";
	}
	
	
	
}
