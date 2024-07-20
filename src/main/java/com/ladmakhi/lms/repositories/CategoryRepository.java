package com.ladmakhi.lms.repositories;

import com.ladmakhi.lms.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByTitle(String title);
    List<Category> findAllByParentIsNull();
    boolean existsByTitleAndIdNot(String title, Long id);
}
