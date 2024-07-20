package com.ladmakhi.lms.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {
    @NotBlank
    private String phone;

    @NotBlank
    private String password;
}
