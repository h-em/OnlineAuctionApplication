package com.sda.auction.dto;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ErrorResponseDto {

    private int code;
    private String message;

}
