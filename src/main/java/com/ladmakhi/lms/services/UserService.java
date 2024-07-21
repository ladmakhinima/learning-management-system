package com.ladmakhi.lms.services;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.user.CreateUserDto;
import com.ladmakhi.lms.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User createUser(CreateUserDto dto) throws DuplicateException;
    User findUserByPhoneAndPassword(String phone, String password) throws UsernameNotFoundException;
    User findUserById(Long id) throws NotFoundException;
    User findUserByPhone(String phone) throws NotFoundException;
}
