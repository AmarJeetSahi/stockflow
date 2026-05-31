package com.stockflow.auth_service.controller;

import com.stockflow.auth_service.dto.LoginRequestDto;
import com.stockflow.auth_service.dto.LoginResponseDto;
import com.stockflow.auth_service.dto.UserDto;
import com.stockflow.auth_service.entity.User;
import com.stockflow.auth_service.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto inputUser) {
        UserDto createdUser = userAuthService.registerUser(inputUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto inputUser) {
        String token = userAuthService.login(inputUser);
        return token;
 }

}


