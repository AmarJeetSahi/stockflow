package com.stockflow.auth_service.service;

import com.stockflow.auth_service.dto.LoginRequestDto;
import com.stockflow.auth_service.dto.LoginResponseDto;
import com.stockflow.auth_service.dto.UserDto;
import com.stockflow.auth_service.entity.User;
import com.stockflow.auth_service.exeption.ResourceNotFoundException;
import com.stockflow.auth_service.repository.UserRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Builder
public class UserAuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserDto registerUser(UserDto inputUser) {

        final UserDto createdUser = UserDto.builder()
                .username(inputUser.getUsername())
                .password(passwordEncoder.encode(inputUser.getPassword()))
                .email(inputUser.getEmail())
                .build();

        User user = modelMapper.map(createdUser, User.class);

        userRepository.save(user);
        return createdUser;

    }

    public UserDto findByEmail(UserDto inputUser) {
        User fetchedUser = (User) userRepository
                .findByEmail(inputUser.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist for this email!"));
        return modelMapper.map(fetchedUser, UserDto.class);

    }

    public String login(LoginRequestDto inputUser) {


        User fetchedUser = (User) userRepository
                .findByEmail(inputUser.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Invalid Email or Password"));


        if (!passwordEncoder.matches(
                inputUser.getPassword(),
                fetchedUser.getPassword())) {


            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Invalid Password");
        }


        LoginRequestDto loginRequestDto = modelMapper.map(fetchedUser, LoginRequestDto.class);
        String token = jwtService.generateToken(loginRequestDto);


        return token;
    }
}
