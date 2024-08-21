package com.ocode.cbrf.dto.impl.user.http;

import com.ocode.cbrf.dto.Dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Sign up request", example = """
        {
        "login": "CrageWalls",
        "password": "springinaction5",
        "role": "ADMIN"
        }
        """)
public class SignUpRequest implements Dto { // регистрация
    private String login;

    private String password;

    private String role;
}