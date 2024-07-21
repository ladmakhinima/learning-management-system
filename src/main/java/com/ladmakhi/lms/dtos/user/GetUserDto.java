package com.ladmakhi.lms.dtos.user;

import com.ladmakhi.lms.models.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String profileUrl;
    private UserRole role;
}
