package com.blackwell.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(exclude = {"book", "user"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
