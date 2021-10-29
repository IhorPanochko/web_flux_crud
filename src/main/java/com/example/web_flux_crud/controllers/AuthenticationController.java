package com.example.web_flux_crud.controllers;

import com.example.web_flux_crud.config.JWTUtil;
import com.example.web_flux_crud.config.PBKDF2Encoder;
import com.example.web_flux_crud.dto.AuthRequest;
import com.example.web_flux_crud.dto.AuthResponse;
import com.example.web_flux_crud.dto.UserDto;
import com.example.web_flux_crud.service.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AuthenticationController {
    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;


    public AuthenticationController(JWTUtil jwtUtil, PBKDF2Encoder passwordEncoder, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody AuthRequest ar) {
        return userService.findByUsername(ar.getUsername())
                .filter(userDetails -> passwordEncoder.encode(ar.getPassword()).equals(userDetails.getPassword()))
                .map(userDetails -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails))))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }


    @PostMapping("/register")
    public Mono<ResponseEntity<Mono<UserDto>>> register(@RequestBody UserDto userDto) {
        return Mono.just(ResponseEntity.ok(userService.saveUser(userDto)));
    }


}
