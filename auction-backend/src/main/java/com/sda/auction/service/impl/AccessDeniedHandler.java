package com.sda.auction.service.impl;

import com.sda.auction.dto.ErrorResponseDto;
import org.springframework.stereotype.Component;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Halip on 17.11.2019.
 */
@Component
public class AccessDeniedHandler {

    public void reply(ServletResponse servletResponse) throws IOException {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(401,"Unauthorized access!");
        byte[] response = errorResponseDto.getMessage().getBytes();
        servletResponse.getOutputStream().write(response);

        ((HttpServletResponse) servletResponse).setHeader("Content-Type","application/json");
        ((HttpServletResponse) servletResponse).setStatus(errorResponseDto.getCode());
    }
}
