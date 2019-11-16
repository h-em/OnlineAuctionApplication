package com.sda.auction.service.impl;

import com.sda.auction.dto.LoginDto;
import com.sda.auction.dto.UserDto;
import com.sda.auction.mapper.UserMapper;
import com.sda.auction.model.Role;
import com.sda.auction.model.User;
import com.sda.auction.repository.RoleRepository;
import com.sda.auction.repository.UserRepository;
import com.sda.auction.service.SecurityService;
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
    private SecurityService securityService;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder, SecurityService securityService, RoleRepository roleRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityService = securityService;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        User user = userMapper.convert(userDto);
        encodePassword(user);//fac hash la parola
        addUserRole(user);
        User userResult = userRepository.save(user);
        return userMapper.convert(userResult);
    }

    private void addUserRole(User user) {
        Role userRole = roleRepository.findByRoleName("user");
        user.addRole(userRole);

        Role adminRole = roleRepository.findByRoleName("admin");
        user.addRole(adminRole);
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

    @Override
    public LoginDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if(user == null){
            throw new RuntimeException("Email address non existent!");
        }
         if(securityService.passwordMatch(loginDto, user)){
            return securityService.createDtoWithJwt(user);
         }
        throw new RuntimeException("Password do not match");
    }


    private void encodePassword(User user) {
        //fac hash la parola
        String passwordedEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordedEncoded);
    }


}
