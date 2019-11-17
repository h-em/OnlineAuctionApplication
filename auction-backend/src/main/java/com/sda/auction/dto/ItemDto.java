package com.sda.auction.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

/**
 * Created by Halip on 17.11.2019.
 */
@Data
@EqualsAndHashCode
public class ItemDto {

    private int id;
    @NotEmpty(message = "Please insert item's name!")
    private String name;
    @NotEmpty(message = "Please insert item's description!")
    private String description;
    @Positive
    private int startingPrice;
    @Positive
    private int currentPrice;
    @NotEmpty
    private String category;
    @NotEmpty
    private String startDate;
    @NotEmpty
    private String endDate;
    private String ownerName;
}
