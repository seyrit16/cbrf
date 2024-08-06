package com.ocode.cbrf.service.web;

import com.ocode.cbrf.dto.impl.user.http.JwtAuthenticationResponse;
import com.ocode.cbrf.dto.impl.user.http.SignInRequest;
import com.ocode.cbrf.dto.impl.user.http.SignUpRequest;
import com.ocode.cbrf.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final CbrfUserDetailsService cbrfUserDetailsService;
    private final AuthenticationManager authenticationManager;

    /**
     * Регистрация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signUp(SignUpRequest request) {
        userService.addUser(request.getLogin(), request.getPassword(), request.getRole());
        String jwt = jwtService.generateToken(cbrfUserDetailsService.loadUserByUsername(request.getLogin()));
        return new JwtAuthenticationResponse(jwt);
    }

    /**
     * Аутентификация пользователя
     *
     * @param request данные пользователя
     * @return токен
     */
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        ));
        String jwt = jwtService.generateToken(cbrfUserDetailsService.loadUserByUsername(request.getLogin()));
        return new JwtAuthenticationResponse(jwt);
    }
}
