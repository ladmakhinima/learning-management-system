package com.ladmakhi.lms.dtos.category;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetCategoryDto {
    private Long id;
    private String title;
}
