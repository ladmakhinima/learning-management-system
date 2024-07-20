package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.dtos.auth.LoginUserDto;
import com.ladmakhi.lms.dtos.user.CreateUserDto;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.services.AuthService;
import com.ladmakhi.lms.services.JsonWebTokenService;
import com.ladmakhi.lms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JsonWebTokenService jwtService;

    @Override
    public String loginUser(LoginUserDto dto) throws UsernameNotFoundException {
        User user = userService.findUserByPhoneAndPassword(dto.getPhone(), dto.getPassword());
        String token = jwtService.generateToken(user);
        return token;
    }

    @Override
    public String signupUser(CreateUserDto dto) throws DuplicateException {
        User user = userService.createUser(dto);
        String token = jwtService.generateToken(user);
        return token;
    }
}
