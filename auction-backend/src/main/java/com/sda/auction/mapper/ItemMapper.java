package com.sda.auction.mapper;


import com.sda.auction.dto.ItemDto;
import com.sda.auction.util.DateConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sda.auction.model.*;

import java.util.Date;

/**
 * Created by Halip on 09.11.2019.
 */
@Service
public class ItemMapper {

    @Autowired
    private DateConvertor dateConvertor;

    public Item convert(ItemDto itemDto){
        Item item = new Item();
        item.setName(itemDto.getName());
        item.setCategory(itemDto.getCategory());
        item.setDescription(itemDto.getDescription());
        item.setCurrentPrice(itemDto.getCurrentPrice());
        item.setStartingPrice(itemDto.getStartingPrice());

        Date startDate = dateConvertor.parsser(itemDto.getStartDate());
        item.setStartDate(startDate);

        Date endDate = dateConvertor.parsser(itemDto.getEndDate());
        item.setEndDate(endDate);

        item.setOwnerName(itemDto.getOwnerName());
        return item;
    }

    public ItemDto convert(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setCategory(item.getCategory());
        itemDto.setDescription(item.getDescription());
        itemDto.setCurrentPrice(item.getCurrentPrice());
        itemDto.setStartingPrice(item.getStartingPrice());

        String startDate = dateConvertor.formatter(item.getStartDate());
        itemDto.setStartDate(startDate);

        String endDate = dateConvertor.formatter(item.getEndDate());
        itemDto.setEndDate(endDate);

        itemDto.setOwnerName(itemDto.getOwnerName());
        return itemDto;
    }
}
