package com.ladmakhi.lms.dtos.category;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetCategoryDetailDto {
    private Long id;
    private String title;
    private List<GetCategoryDetailDto> children;
}
