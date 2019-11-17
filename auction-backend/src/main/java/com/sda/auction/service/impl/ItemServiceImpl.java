package com.sda.auction.service.impl;

import com.sda.auction.dto.ItemDto;
import com.sda.auction.mapper.ItemMapper;
import com.sda.auction.model.Item;
import com.sda.auction.model.User;
import com.sda.auction.repository.ItemRepository;
import com.sda.auction.repository.UserRepository;
import com.sda.auction.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Halip on 09.11.2019.
 */
@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;
    private ItemMapper itemMapper;
    private UserRepository userRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository,
                           ItemMapper itemMapper, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.userRepository = userRepository;
    }


    @Override
    public ItemDto addItem(ItemDto itemDto, String ownerEmail) {
        User owner = userRepository.findByEmail(ownerEmail);
        Item item = itemMapper.convert(itemDto);
        item.setOwner(owner);

        Item savedItem = itemRepository.save(item);
        return itemMapper.convert(savedItem);
    }

    @Override
    public List<ItemDto> getAllItems() {
        return null;
    }
}
