package com.ocode.cbrf.dto.impl.user.http;

import com.ocode.cbrf.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse implements Dto {
    private String token;
}