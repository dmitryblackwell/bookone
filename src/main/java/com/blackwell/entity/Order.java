package com.blackwell.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GenericGenerator(name = "sequence_dep_id", strategy = "com.blackwell.util.OrderNoGenerator")
    @GeneratedValue(generator = "sequence_dep_id")
    private String id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Book book;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;
}
