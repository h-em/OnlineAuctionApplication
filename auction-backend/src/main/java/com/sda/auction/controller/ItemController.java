package com.sda.auction.controller;

import com.sda.auction.dto.ItemDto;
import com.sda.auction.dto.LoginDto;
import com.sda.auction.dto.UserDto;
import com.sda.auction.service.ItemService;
import com.sda.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Halip on 09.11.2019.
 */
@RestController
@RequestMapping("/api/admin/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ItemDto>> get() {
        List<ItemDto> itemDtoList = itemService.getAllItems();
        return new ResponseEntity<>(itemDtoList, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ItemDto> post(@Valid @RequestBody ItemDto itemDto) {
        String ownerEmail = "ionel.radu@yahoo.com";
        ItemDto finalItemDto = itemService.addItem(itemDto, ownerEmail);
        return new ResponseEntity<>(finalItemDto, HttpStatus.OK);
    }

}
