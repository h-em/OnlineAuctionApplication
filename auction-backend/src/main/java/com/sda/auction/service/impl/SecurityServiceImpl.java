package com.sda.auction.service.impl;

import com.sda.auction.dto.LoginDto;
import com.sda.auction.jwt.TokenProvider;
import com.sda.auction.model.User;
import com.sda.auction.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Halip on 16.11.2019.
 */
@Service
public class SecurityServiceImpl implements SecurityService {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private TokenProvider tokenProvider;

    @Override
    public boolean passwordMatch(LoginDto loginDto, User user) {
        //imi compara singur parola in plane text de pe front cu parola hasuita din db
        String plainTextPassword = loginDto.getPassword();
        String hashedPassword = user.getPassword();
        return passwordEncoder.matches(plainTextPassword, hashedPassword);
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

    @Override
    public boolean isValid(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String requestURL = httpServletRequest.getRequestURI();
        String jwt = resolveToken(httpServletRequest);
        return tokenProvider.validate(jwt,requestURL);
    }

    private String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(("Bearer "))){
            return bearerToken.substring(7);
        }
        return null;
    }
}
