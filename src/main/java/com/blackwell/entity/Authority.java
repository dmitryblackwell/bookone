package com.blackwell.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="authorities")
public class Authority implements Serializable {


    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    @Column(name="authority")
    private String authority;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}
