package com.sda.auction.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "owner",
            cascade = CascadeType.ALL)
    private Set<Item> items = new HashSet<>();


    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL)
    private Set<Bid> bids = new HashSet<>();


    public void addRole(Role role) {
        roles.add(role);
    }

    public List<String> getRolesAsString() {
        List<String> result = new ArrayList<>();
        for(Role role: roles){
            result.add(role.getRoleName());
        }
        return result;
    }
}
