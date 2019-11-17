package com.sda.auction.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Halip on 09.11.2019.
 */
@Entity
@Table
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int id;
    @Column
    @NotEmpty(message = "Please insert item's name!")
    private String name;
    @Column
    @NotEmpty(message = "Please insert item's description!")
    private String description;
    @Column
    @Positive
    private int startingPrice;
    @Column
    @Positive
    private int currentPrice;
    @Column
    @NotEmpty
    private String category;
    @Column
    @NotEmpty
    private Date startDate;
    @Column
    @NotEmpty
    private Date endDate;
    @Column
    private String ownerName;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "item")
    private Set<Bid> bids = new HashSet<>();

}
