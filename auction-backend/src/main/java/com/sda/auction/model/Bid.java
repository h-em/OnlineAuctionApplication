package com.sda.auction.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Halip on 17.11.2019.
 */

@Entity
@Table
@Data
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="bid_id")
    private int id;
    @Column
    private int price;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id")
    private Item item;
}
