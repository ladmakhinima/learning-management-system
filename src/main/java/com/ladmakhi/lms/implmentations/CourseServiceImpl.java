package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.course.CourseUploadImageOutputDto;
import com.ladmakhi.lms.dtos.course.CourseUploadVideoOutputDto;
import com.ladmakhi.lms.dtos.course.CreateCourseDto;
import com.ladmakhi.lms.models.Category;
import com.ladmakhi.lms.models.Course;
import com.ladmakhi.lms.models.CourseStatus;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.repositories.CourseRepository;
import com.ladmakhi.lms.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final ImageUploader imageUploader;
    private final VideoUploader videoUploader;
    private final CourseRepository courseRepository;
    private final CategoryService categoryService;
    private final UserService userService;

    @Override
    public CourseUploadImageOutputDto uploadImage(MultipartFile file) throws IOException {
        String image = imageUploader.uploadAndResizeImage(600, 600, file);
        String thumbnail = imageUploader.uploadAndResizeImage(300, 400, file);
        return CourseUploadImageOutputDto.builder()
                .image(image)
                .thumbnail(thumbnail)
                .build();
    }

    @Override
    public CourseUploadVideoOutputDto uploadVideo(MultipartFile file) throws IOException {
        String videoUrl = videoUploader.uploadVideo(file);
        return CourseUploadVideoOutputDto.builder()
                .video(videoUrl)
                .build();
    }

    @Override
    public Course findCourseById(Long id) throws NotFoundException {
        return courseRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("دوره مورد نظر یافت نشد"));
    }

    @Override
    public Course createCourse(CreateCourseDto dto) throws DuplicateException, NotFoundException {
        if (courseRepository.existsByTitle(dto.getTitle())) {
            throw new DuplicateException("عنوان دوره تکراری میباشد");
        }
        Category category = categoryService.findCategoryById(dto.getCategoryId());
        User teacher = userService.findUserById(dto.getTeacherId());
        return courseRepository.save(
                Course.builder()
                        .title(dto.getTitle())
                        .description(dto.getDescription())
                        .level(dto.getLevel())
                        .status(CourseStatus.PENDING)
                        .price(dto.getPrice())
                        .teacher(teacher)
                        .category(category)
                        .thumbnail(dto.getThumbnail())
                        .image(dto.getImage())
                        .build()
        );
    }

    @Override
    public Course deleteCourseById(Long id) throws NotFoundException {
        Course course = findCourseById(id);
        courseRepository.delete(course);
        return course;
    }

    @Override
    @Cacheable(value = "courses", key = "'getCourses'")
    public List<Course> getCourses() {
        return courseRepository.findAll(
                Sort.by(Sort.Direction.DESC, "id")
        );
    }

    @Override
    public List<Course> getCoursesByIds(List<Long> ids) throws NotFoundException {
        List<Course> courses = courseRepository.findCoursesByIds(ids);
        if (courses.isEmpty()) {
            throw new NotFoundException("دوره ای یافت نشد");
        }
        return courses;
    }
}
