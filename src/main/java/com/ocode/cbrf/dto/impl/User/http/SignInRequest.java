package com.ocode.cbrf.dto.impl.user.http;

import com.ocode.cbrf.dto.Dto;
import lombok.Data;

@Data
public class SignInRequest implements Dto { // аутентификация
    private String login;

    private String password;
}