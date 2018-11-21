package com.bookshelf.entity;

import javax.persistence.*;

@Entity
@Table(name="comments")
public class Comment {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="isbn")
    private Book book;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="username")
    private User user;

    @Column(name="comment")
    private String comment;

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
