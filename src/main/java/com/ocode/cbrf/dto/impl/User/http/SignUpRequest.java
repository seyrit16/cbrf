package com.ocode.cbrf.dto.impl.user.http;

import com.ocode.cbrf.dto.Dto;
import lombok.Data;

@Data
public class SignUpRequest implements Dto { // регистрация
    private String login;

    private String password;

    private String role;
}