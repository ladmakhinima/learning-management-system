package com.ladmakhi.lms.dtos.course;

import com.ladmakhi.lms.models.CourseLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCourseDto {
    private String title;
    private String description;
    private Long categoryId;
    private Long teacherId;
    private String image;
    private String thumbnail;
    private Double price;
    private CourseLevel level;
}
