package com.ladmakhi.lms.repositories;

import com.ladmakhi.lms.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTitle(String title);

    @Query("SELECT c FROM Course c where c.id in :coursesIds")
    List<Course> findCoursesByIds(@Param("coursesIds") List<Long> coursesId);
}
