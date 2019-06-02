package com.blackwell.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phoneNumber;
    private Date creationDate;
    private boolean enabled;
    // TODO add set of authorities
    @Enumerated(EnumType.ORDINAL)
    private UserLevel level;
}
