package com.sda.auction.controller;

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
@RequestMapping("/api/register")
public class RegistrationController {


    private UserService userService;
    private UserDtoValidator userDtoValidator;

    @Autowired
    public RegistrationController( UserService userService, UserDtoValidator userDtoValidator){
        this.userService = userService;
        this.userDtoValidator =  userDtoValidator;
    }
/*
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> login(@Valid @RequestParam(value = "email") String email,
                                        @RequestParam(value="password") String password){
        userDtoValidator.validate(email, password);
        return new ResponseEntity<>("login suCceSs!",HttpStatus.OK);
    }
*/

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<UserDto>> get() {
        List<UserDto> userDtoList = userService.getAllUsers();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> post(@Valid @RequestBody UserDto userDto) {
        userDtoValidator.validate(userDto);
        UserDto finalUserDto = userService.addUser(userDto);
        return new ResponseEntity<>(finalUserDto, HttpStatus.OK);
    }

/*
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> put(@Valid @RequestBody UserDto userDto){

        userService.updateUserByEmail(userDto);
        return new ResponseEntity<>(finalUserDto, HttpStatus.OK);
    }*/
}
