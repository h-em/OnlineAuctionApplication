package com.sda.auction.service;

import com.sda.auction.dto.LoginDto;
import com.sda.auction.dto.UserDto;
import com.sda.auction.model.User;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Halip on 09.11.2019.
 */
public interface UserService {

    UserDto addUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto updateUser(UserDto userDto);
    User findByEmail(String email);

    LoginDto login(LoginDto userDto);
}
