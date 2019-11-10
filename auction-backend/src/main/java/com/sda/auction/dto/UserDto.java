package com.sda.auction.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Halip on 09.11.2019.
 */

@Data
@EqualsAndHashCode
public class UserDto {

    private int id;
    @NotEmpty
    @Pattern(regexp = "[A-Za-z]+",message = "Letters only!")
    private String firstName;
    @NotEmpty
    @Pattern(regexp = "[A-Za-z]+", message = "Letters only!")
    private String lastName;
    @Email(message = "{error.user.email.pattern}")
    private String email;
    @NotEmpty
    @Pattern(regexp = "((.*)[A-Z]+(.*))", message = "Password should contain least one capital letter!")
    @Size(min = 6, message = "Password should be at least 6 characters long!")
    private String password;
}
