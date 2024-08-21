package com.ocode.cbrf.dto.impl.user.http;

import com.ocode.cbrf.dto.Dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "JWT authentication response", example = """
        {
        "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiQURNSU4iLCJpZCI6MSwibG9naW4iOiJhZG1pbjEiLCJzdWIiOiJhZG1pbjEiLCJpYXQiOjE3MjQxOTkzNzcsImV4cCI6MTcyNDM0MzM3N30._-IPgxCcvAaCF5s-BBkdMIh-pDPmouMVRxmZS1uUVYg"
        }""")
public class JwtAuthenticationResponse implements Dto {
    private String token;
}