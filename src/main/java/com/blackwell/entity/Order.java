package com.blackwell.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = {"user", "book"})
@EqualsAndHashCode(exclude = {"user", "book", "status", "quantity"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public Order(User user, Book book, int quantity) {
        this.user = user;
        this.book = book;
        this.quantity = quantity;
    }
}
