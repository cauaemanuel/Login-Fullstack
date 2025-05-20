package com.authlogin.service;

import com.authlogin.domain.User;
import com.authlogin.dto.LoginRequestDTO;
import com.authlogin.dto.RegisterRequestDTO;
import com.authlogin.dto.ResponseDTO;
import com.authlogin.infra.security.TokenService;
import com.authlogin.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Transactional
    public ResponseDTO login(LoginRequestDTO body){
        User user = this.repository
                .findByEmail(body.email())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
        if (passwordEncoder.matches(body.password(), user.getPassword())){
            var token = tokenService.generateToken(user);
            return new ResponseDTO(user.getName(), token);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public ResponseDTO register(RegisterRequestDTO body){
        var user = repository.findByEmail(body.email());
        if (user.isEmpty()){
            var newUser = new User();
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            var token = tokenService.generateToken(newUser);
            repository.save(newUser);
            return new ResponseDTO(newUser.getName(), token);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
