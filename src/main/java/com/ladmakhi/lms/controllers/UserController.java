package com.ladmakhi.lms.controllers;

import com.ladmakhi.lms.dtos.user.GetUserDto;
import com.ladmakhi.lms.mappers.UserMapper;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/page")
    public ResponseEntity<List<GetUserDto>> getUsersPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "limit", required = false, defaultValue = "1") int limit
    ) {
        List<User> users = userService.findUsersPage(
                PageRequest.of(page, limit)
        );
        List<GetUserDto> responseDto = userMapper.mapUsersToListOfGetUserDto(users);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
