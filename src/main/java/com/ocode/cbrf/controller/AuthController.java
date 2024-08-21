package com.ocode.cbrf.controller;

import com.ocode.cbrf.dto.impl.user.http.JwtAuthenticationResponse;
import com.ocode.cbrf.dto.impl.user.http.SignInRequest;
import com.ocode.cbrf.dto.impl.user.http.SignUpRequest;
import com.ocode.cbrf.service.web.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication controller")
public class AuthController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/sign-up")
    @Operation(summary = "Sign up")
    public JwtAuthenticationResponse signUp(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Sign up request",
                    content = @Content(mediaType = "application/json"))
            @RequestBody SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Sign in")
    public JwtAuthenticationResponse signIn(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Sign in request",
                    content = @Content(mediaType = "application/json"))
            @RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}