package com.ocode.cbrf.dto.impl.user.http;

import com.ocode.cbrf.dto.Dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Sign in request", example = """
        {
        "login": "CrageWalls",
        "password": "springinaction5"
        }
        """)
public class SignInRequest implements Dto { // аутентификация
    private String login;

    private String password;
}