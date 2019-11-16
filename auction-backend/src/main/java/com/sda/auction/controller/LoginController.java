package com.sda.auction.controller;

import com.sda.auction.dto.LoginDto;
import com.sda.auction.dto.UserDto;
import com.sda.auction.service.UserService;
import com.sda.auction.validator.UserDtoValidator;
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
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<LoginDto> post(@Valid @RequestBody LoginDto userDto) {
        LoginDto loginDtoResult = userService.login(userDto);
        return new ResponseEntity<>(loginDtoResult, HttpStatus.OK);
    }

}
