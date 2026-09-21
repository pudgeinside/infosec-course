package edu.itmo.infosec_lab1.controller;

import edu.itmo.infosec_lab1.dto.LoginRequest;
import edu.itmo.infosec_lab1.dto.RegisterRequest;
import edu.itmo.infosec_lab1.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService auth;

    @PostMapping("/auth/login")
    public String login(@Valid @RequestBody LoginRequest request) {
        return auth.login(request);
    }

    @PostMapping("/auth/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return auth.register(request);
    }
}