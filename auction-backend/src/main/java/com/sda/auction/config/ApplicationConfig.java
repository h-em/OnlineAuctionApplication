package com.sda.auction.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Created by Halip on 09.11.2019.
 */
@Configuration
public class ApplicationConfig {



    //FOR ERROR MESSAGES
    @Bean(name = "messageSource")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/validation/message");
        return messageSource;
    }

    //FOR ERROR MESSAGES
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }


    //PENTRU CRIPTAREA PAROLEI
    @Bean
    public BCryptPasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
