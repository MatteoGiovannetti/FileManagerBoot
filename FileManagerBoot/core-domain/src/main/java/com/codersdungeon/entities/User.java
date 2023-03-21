package com.codersdungeon.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    private String username;

    @Column (unique = true)
    private String email;

    @Column
    private String password;


}
