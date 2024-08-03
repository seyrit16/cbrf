package com.ocode.cbrf.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SecurityController {
    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @GetMapping("/csrf")
    public ResponseEntity<String> getCsrfToken(HttpServletRequest request) {
        String csrfToken = (String) request.getAttribute("_csrf");
        return ResponseEntity.ok(csrfToken);
    }
}
