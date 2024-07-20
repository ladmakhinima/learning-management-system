package com.ladmakhi.lms.mappers;

import com.ladmakhi.lms.dtos.category.GetCategoryDetailDto;
import com.ladmakhi.lms.dtos.category.GetCategoryDto;
import com.ladmakhi.lms.models.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    GetCategoryDto mapCategoryToGetCategoryDto(Category category);

    List<GetCategoryDto> mapCategoriesToPageGetCategoryDetailDto(List<Category> categories);

    GetCategoryDetailDto mapCategoryToGetCategoryDetailDto(Category category);
}
