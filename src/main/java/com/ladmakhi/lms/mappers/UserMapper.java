package com.ladmakhi.lms.mappers;

import com.ladmakhi.lms.dtos.user.GetUserDto;
import com.ladmakhi.lms.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    List<GetUserDto> mapUsersToListOfGetUserDto(List<User> users);
}
