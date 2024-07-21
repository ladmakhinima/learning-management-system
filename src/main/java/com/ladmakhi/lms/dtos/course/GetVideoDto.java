package com.ladmakhi.lms.dtos.course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVideoDto {
    private Long id;
    private String title;
    private String description;
    private String videoUrl;
}
