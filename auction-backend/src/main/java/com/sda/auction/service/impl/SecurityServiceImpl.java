package com.sda.auction.service.impl;

import com.sda.auction.dto.LoginDto;
import com.sda.auction.jwt.TokenProvider;
import com.sda.auction.model.User;
import com.sda.auction.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by Halip on 16.11.2019.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public boolean passwordMatch(LoginDto loginDto, User user) {
        //imi compara singur parola in plane text de pe front cu parola hasuita din db
        return passwordEncoder.matches(loginDto.getPassword(),user.getPassword());
    }

    @Override
    public LoginDto createDtoWithJwt(User user) {
        LoginDto result = new LoginDto();
        result.setEmail(user.getEmail());
        result.setPassword(user.getPassword());

        String jwt = tokenProvider.createJwt(user);
        result.setJwt(jwt);
        return result;
    }
}
