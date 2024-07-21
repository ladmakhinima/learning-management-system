package com.ladmakhi.lms.dtos.category;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCourseCategory {
    private Long id;
    private String title;
    private GetCourseCategory parent;
}
