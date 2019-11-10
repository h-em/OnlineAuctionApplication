package com.sda.auction.service.impl;

import com.sda.auction.dto.UserDto;
import com.sda.auction.mapper.UserMapper;
import com.sda.auction.model.User;
import com.sda.auction.repository.UserRepository;
import com.sda.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Halip on 09.11.2019.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userMapper.convert(userDto);
        //fac hash la parola
        encodePassword(user);
        User userResult = userRepository.save(user);
        return userMapper.convert(userResult);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new LinkedList<>();
        for (User user : userList) {
            userDtoList.add(userMapper.convert(user));
        }
        return userDtoList;
    }

    @Override
    public UserDto updateUser(UserDto userDto) {

        User user = userRepository.findByEmail(userDto.getEmail());

        return null;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    private void encodePassword(User user) {
        //fac hash la parola
        String passwordedEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordedEncoded);
    }


}
