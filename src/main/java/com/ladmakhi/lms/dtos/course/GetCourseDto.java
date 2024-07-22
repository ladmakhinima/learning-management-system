package com.ladmakhi.lms.dtos.course;

import com.ladmakhi.lms.dtos.category.GetCourseCategory;
import com.ladmakhi.lms.dtos.user.GetUserDto;
import com.ladmakhi.lms.models.CourseLevel;
import com.ladmakhi.lms.models.CourseStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCourseDto {
    private Long id;
    private String title;
    private List<GetVideoDto> videos;
    private GetCourseCategory category;
    private CourseLevel level;
    private CourseStatus status;
    private Double price;
    private String description;
    private String image;
    private String thumbnail;
    private GetUserDto teacher;
}
