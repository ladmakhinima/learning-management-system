package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.category.CreateCategoryDto;
import com.ladmakhi.lms.dtos.category.UpdateCategoryDto;
import com.ladmakhi.lms.models.Category;
import com.ladmakhi.lms.repositories.CategoryRepository;
import com.ladmakhi.lms.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Override
    public Category createCategory(CreateCategoryDto dto) throws DuplicateException, NotFoundException {
        if (categoryRepository.existsByTitle(dto.getTitle())) {
            throw new DuplicateException("عنوان دسته بندی قبلا ثبت شده است");
        }
        var categoryBuilder = Category.builder()
                .title(dto.getTitle());
        if (dto.getParentId() != null) {
            categoryBuilder.parent(
                    findCategoryById(dto.getParentId())
            );
        }
        return categoryRepository.save(
                categoryBuilder.build()
        );
    }

    @Override
    public Category deleteCategoryById(Long id) throws NotFoundException {
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public Category findCategoryById(Long id) throws NotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("دسته بندی با این شناسه یافت نشد"));
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAllByParentIsNull();
    }

    @Override
    public Category updateCategoryById(Long id, UpdateCategoryDto dto) throws NotFoundException, DuplicateException {
        Category category = findCategoryById(id);
        if (categoryRepository.existsByTitleAndIdNot(dto.getTitle(), id)) {
            throw new DuplicateException("عنوان دسته بندی قبلا ثبت شده است");
        }
        category.setParent(
                findCategoryById(
                        dto.getParentId()
                )
        );
        category.setTitle(dto.getTitle());
        return categoryRepository.save(category);
    }
}
