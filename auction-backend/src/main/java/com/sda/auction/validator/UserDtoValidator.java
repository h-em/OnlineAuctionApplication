package com.sda.auction.validator;

import com.sda.auction.dto.UserDto;
import com.sda.auction.model.User;
import com.sda.auction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Halip on 10.11.2019.
 */
@Component
public class UserDtoValidator {
    @Autowired
    private UserService userService;

    public boolean validate(UserDto userDto){

        if(passwordDontMatch(userDto)){
            throw new RuntimeException("Password don't match!");
        }else{
            if (emailAlreadyRegistered(userDto.getEmail())) {
                throw new RuntimeException("Email already registered!");
            }
        }
        return true;
    }

    public boolean validate(String email, String password){
        User user = userService.findByEmail(email);
        if(user == null){
            throw new RuntimeException("Email dind't exist!");
        }
        if(!isPasswordMatching(user.getPassword(),password)){
            throw new RuntimeException("Password dind't match!");
        }
            return true;
    }

    private boolean isPasswordMatching(String dbPassword, String insertedPassword) {
        return dbPassword.compareTo(insertedPassword) == 0;
    }

    private boolean passwordDontMatch(UserDto userDto) {
        return userDto.getPassword().compareTo(userDto.getConfirmPassword()) != 0;
    }

    private boolean emailAlreadyRegistered(String email) {
        User user = userService.findByEmail(email);
        return user != null;
    }


}
