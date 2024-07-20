package com.ladmakhi.lms.controllers;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.dtos.auth.AuthResponseDto;
import com.ladmakhi.lms.dtos.auth.LoginUserDto;
import com.ladmakhi.lms.dtos.user.CreateUserDto;
import com.ladmakhi.lms.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(
            @RequestBody @Valid LoginUserDto dto
    ) throws UsernameNotFoundException {
        String token = authService.loginUser(dto);
        AuthResponseDto responseDto = new AuthResponseDto(token);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signupUser(
            @RequestBody @Valid CreateUserDto dto
    ) throws DuplicateException {
        String token = authService.signupUser(dto);
        AuthResponseDto responseDto = new AuthResponseDto(token);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
