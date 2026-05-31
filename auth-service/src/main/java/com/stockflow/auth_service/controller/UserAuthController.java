package com.stockflow.auth_service.controller;

import com.stockflow.auth_service.dto.LoginRequestDto;
import com.stockflow.auth_service.dto.LoginResponseDto;
import com.stockflow.auth_service.dto.UserDto;
import com.stockflow.auth_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto inputUser) {
        UserDto createdUser = userService.registerUser(inputUser);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto inputUser) {
        LoginResponseDto loggedInUser = userService.login(inputUser);
        return ResponseEntity.ok(loggedInUser);
 }
}


