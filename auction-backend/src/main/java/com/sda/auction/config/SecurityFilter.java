package com.sda.auction.config;

import com.sda.auction.service.SecurityService;
import com.sda.auction.service.impl.AccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Halip on 17.11.2019.
 */
@Component
@Order(1)///se executa inainte de cotroller
public class SecurityFilter implements Filter{

    @Autowired
    private SecurityService securityService;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Hello!");

        if(securityService.isValid(servletRequest)){
            ///continua sa faci filtrarea
            filterChain.doFilter(servletRequest,servletResponse);}
        else{
            ///measj de replay
            accessDeniedHandler.reply(servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
