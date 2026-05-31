package com.stockflow.auth_service.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private Long id;
    private String username;
    private String email;
}