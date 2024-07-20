package com.ladmakhi.lms.controllers;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.category.CreateCategoryDto;
import com.ladmakhi.lms.dtos.category.GetCategoryDetailDto;
import com.ladmakhi.lms.dtos.category.GetCategoryDto;
import com.ladmakhi.lms.dtos.category.UpdateCategoryDto;
import com.ladmakhi.lms.mappers.CategoryMapper;
import com.ladmakhi.lms.models.Category;
import com.ladmakhi.lms.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<GetCategoryDto> createCategory(
            @RequestBody @Valid CreateCategoryDto dto
    ) throws DuplicateException, NotFoundException {
        Category category = categoryService.createCategory(dto);
        GetCategoryDto responseDto = categoryMapper.mapCategoryToGetCategoryDto(category);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<GetCategoryDto> deleteCategoryId(
            @PathVariable("id") Long id
    ) throws NotFoundException {
        Category category = categoryService.deleteCategoryById(id);
        GetCategoryDto responseDto = categoryMapper.mapCategoryToGetCategoryDto(category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GetCategoryDto>> getCategories() {
        List<Category> categories = categoryService.findAllCategories();
        var responseDto = categoryMapper.mapCategoriesToPageGetCategoryDetailDto(categories);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCategoryDetailDto> getCategoryById(
            @PathVariable("id") Long id
    ) throws NotFoundException {
        Category category = categoryService.findCategoryById(id);
        GetCategoryDetailDto responseDto = categoryMapper.mapCategoryToGetCategoryDetailDto(category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<GetCategoryDto> updateCategoryById(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateCategoryDto dto
    ) throws NotFoundException, DuplicateException {
        Category category = categoryService.updateCategoryById(id, dto);
        GetCategoryDto responseDto = categoryMapper.mapCategoryToGetCategoryDto(category);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
