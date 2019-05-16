package com.blackwell.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @Column(name="orderNo")
    @GenericGenerator(name = "sequence_dep_id", strategy = "com.blackwell.util.OrderNoGenerator")
    @GeneratedValue(generator = "sequence_dep_id")
    private String orderNo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="username")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="isbn")
    private Book book;

    @Column(name="status")
    private int status;

    @Column(name="quantity")
    private int quantity;

    public Order() {}
    public Order(String orderNo) {
        this.orderNo = orderNo;
    }
    public Order(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public Order(User user, Book book, int quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderNo='" + orderNo + '\'' +
                ", book=" + book.getIsbn() +
                ", status=" + status +
                '}';
    }

//    @Override
//    public int hashCode() {
//        int hash = 0;
//        for(int i=0; i<orderNo.length(); ++i)
//            hash += 31*orderNo.charAt(i);
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (!(obj instanceof Order))
//            return false;
//        if (obj == this)
//            return true;
//
//        return orderNo.equals(((Order) obj).orderNo);
//    }
}
