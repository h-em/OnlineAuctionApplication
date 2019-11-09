package com.sda.auction.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Halip on 09.11.2019.
 */
@Entity
@Table
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String email;
    @Column
    private String password;
}