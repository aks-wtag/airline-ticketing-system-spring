package com.ats.model.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public abstract class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    @Column(nullable = false)
    private String userFullName;
    @Column(nullable = false)
    private String userPassword;
    @Column(unique = true, nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private String userContact;
}
