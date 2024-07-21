package com.ladmakhi.lms.dtos.comment;

import com.ladmakhi.lms.dtos.course.GetCourseDto;
import com.ladmakhi.lms.dtos.user.GetUserDto;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetCommentDto {
    private Long id;
    private GetUserDto author;
    private String content;
    private List<GetCommentDto> children = new ArrayList<>();
}
