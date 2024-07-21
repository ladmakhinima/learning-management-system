package com.ladmakhi.lms.dtos.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignVideoToCourseDto {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    @Positive
    private Long courseId;

    @NotBlank
    private String videoUrl;
}
