package com.ladmakhi.lms.dtos.course;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateVideoDetailDto {
    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
