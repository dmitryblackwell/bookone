package com.blackwell.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @Column(name="id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="isbn")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="username")
    private User user;

    @Column(name="comment")
    private String comment;

    public Comment() {}
    public Comment(Book book, User user, String comment) {
        this.book = book;
        this.user = user;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
