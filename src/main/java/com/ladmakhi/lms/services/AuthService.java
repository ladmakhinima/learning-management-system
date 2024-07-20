package com.ladmakhi.lms.services;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.dtos.auth.LoginUserDto;
import com.ladmakhi.lms.dtos.user.CreateUserDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface AuthService {
    String loginUser(LoginUserDto dto) throws UsernameNotFoundException;

    String signupUser(CreateUserDto dto) throws DuplicateException;
}
