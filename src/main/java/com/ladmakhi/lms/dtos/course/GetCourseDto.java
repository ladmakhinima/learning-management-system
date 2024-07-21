package com.ladmakhi.lms.dtos.course;

import com.ladmakhi.lms.dtos.category.GetCategoryDto;
import com.ladmakhi.lms.dtos.category.GetCourseCategory;
import com.ladmakhi.lms.dtos.comment.GetCommentDto;
import com.ladmakhi.lms.dtos.user.GetUserDto;
import com.ladmakhi.lms.models.Comment;
import com.ladmakhi.lms.models.CourseLevel;
import com.ladmakhi.lms.models.CourseStatus;
import com.ladmakhi.lms.models.Video;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCourseDto {
    private Long id;
    private String title;
    private List<GetCommentDto> comments;
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
