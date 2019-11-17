package com.sda.auction.service;

import com.sda.auction.dto.ItemDto;
import com.sda.auction.dto.LoginDto;
import com.sda.auction.dto.UserDto;
import com.sda.auction.model.User;

import java.util.List;

/**
 * Created by Halip on 09.11.2019.
 */
public interface ItemService {

    ItemDto addItem(ItemDto itemDto, String ownerEmail);
    List<ItemDto> getAllItems();
}
