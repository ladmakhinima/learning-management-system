package com.ladmakhi.lms.services;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.course.CourseUploadImageOutputDto;
import com.ladmakhi.lms.dtos.course.CourseUploadVideoOutputDto;
import com.ladmakhi.lms.dtos.course.CreateCourseDto;
import com.ladmakhi.lms.models.Course;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CourseService {
    CourseUploadImageOutputDto uploadImage(MultipartFile file) throws IOException;

    CourseUploadVideoOutputDto uploadVideo(MultipartFile file) throws IOException;

    Course findCourseById(Long id) throws NotFoundException;

    Course createCourse(CreateCourseDto dto) throws DuplicateException, NotFoundException;

    Course deleteCourseById(Long id) throws NotFoundException;

    List<Course> getCourses();

    List<Course> getCoursesByIds(List<Long> ids) throws NotFoundException;
}
