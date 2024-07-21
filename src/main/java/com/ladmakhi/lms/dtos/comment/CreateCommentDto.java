package com.ladmakhi.lms.dtos.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentDto {
    @NotBlank
    private String content;

    @NotNull
    @Positive
    private Long courseId;

    @Positive
    private Long parentId;
}
