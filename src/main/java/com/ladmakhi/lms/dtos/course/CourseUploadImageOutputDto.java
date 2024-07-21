package com.ladmakhi.lms.dtos.course;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CourseUploadImageOutputDto {
    private String thumbnail;
    private String image;
}
