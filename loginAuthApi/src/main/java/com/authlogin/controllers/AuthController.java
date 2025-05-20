package com.authlogin.controllers;

import com.authlogin.domain.User;
import com.authlogin.dto.LoginRequestDTO;
import com.authlogin.dto.RegisterRequestDTO;
import com.authlogin.dto.ResponseDTO;
import com.authlogin.infra.security.TokenService;
import com.authlogin.repository.UserRepository;
import com.authlogin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
       var login = authService.login(body);
       return ResponseEntity.ok(login);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body){
        var register = authService.register(body);
        return ResponseEntity.ok(register);
    }

}
