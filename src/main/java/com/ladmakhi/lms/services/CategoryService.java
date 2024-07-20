package com.ladmakhi.lms.services;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.category.CreateCategoryDto;
import com.ladmakhi.lms.dtos.category.UpdateCategoryDto;
import com.ladmakhi.lms.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface CategoryService {
    Category createCategory(CreateCategoryDto dto) throws DuplicateException, NotFoundException;

    Category deleteCategoryById(Long id) throws NotFoundException;

    Category findCategoryById(Long id) throws NotFoundException;

    List<Category> findAllCategories();

    Category updateCategoryById(Long id, UpdateCategoryDto dto) throws NotFoundException, DuplicateException;
}
