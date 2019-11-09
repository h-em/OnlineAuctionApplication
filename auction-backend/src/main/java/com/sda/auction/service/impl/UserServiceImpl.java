package com.sda.auction.service.impl;

import com.sda.auction.dto.UserDto;
import com.sda.auction.mappert.UserMapper;
import com.sda.auction.model.User;
import com.sda.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Halip on 09.11.2019.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userMapper.convert(userDto);
        return null;
    }
}
