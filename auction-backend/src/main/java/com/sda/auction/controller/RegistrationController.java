package com.sda.auction.controller;

import com.sda.auction.dto.UserDto;
import com.sda.auction.model.User;
import com.sda.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

/**
 * Created by Halip on 09.11.2019.
 */
@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserDto>> get() {
        List<UserDto> userDtoList = userService.getAllUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> post(@Valid @RequestBody UserDto userDto) {

        if(emailAlreadyRegistered(userDto.getEmail())){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        //UserDto finalUserDto = userService.addUser(userDto);
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.OK);
    }

    private boolean emailAlreadyRegistered(String email) {
        User user = userService.findByEmail(email);
        return user != null;
    }
/*
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> put(@Valid @RequestBody UserDto userDto){

        userService.updateUserByEmail(userDto);
        return new ResponseEntity<>(finalUserDto, HttpStatus.OK);
    }*/
}
