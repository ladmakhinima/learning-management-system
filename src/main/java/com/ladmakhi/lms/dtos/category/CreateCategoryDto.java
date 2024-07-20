package com.ladmakhi.lms.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryDto {
    @NotBlank(message = "عنوان دسته بندی را وارد کنید")
    @Size(min = 3, message = "عنوان دسته بندی باید حداقل بیشتر از 3 کاراکتر باشد")
    private String title;

    @Positive(message = "سریال دسته بندی انتخاب شده نادرست میباشد")
    private Long parentId;
}
