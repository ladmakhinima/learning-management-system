package com.ladmakhi.lms.dtos.user;

import com.ladmakhi.lms.models.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    @NotBlank
    @Size(min = 11, max = 11)
    private String phone;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Size(min = 3)
    private String firstName;

    @NotBlank
    @Size(min = 3)
    private String lastName;

    @NotNull
    private UserRole role;

    private String bio;

    private String profileUrl;
}
