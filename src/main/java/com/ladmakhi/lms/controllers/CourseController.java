package com.ladmakhi.lms.controllers;

import com.ladmakhi.lms.common.exception.DuplicateException;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.course.*;
import com.ladmakhi.lms.mappers.CourseMapper;
import com.ladmakhi.lms.models.Course;
import com.ladmakhi.lms.models.Video;
import com.ladmakhi.lms.services.CourseService;
import com.ladmakhi.lms.services.VideoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final VideoService videoService;

    @PostMapping("upload-image")
    public ResponseEntity<CourseUploadImageOutputDto> uploadImage(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        CourseUploadImageOutputDto responseDto = courseService.uploadImage(file);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("upload-video")
    public ResponseEntity<?> uploadVideo(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        CourseUploadVideoOutputDto responseDto = courseService.uploadVideo(file);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("assign-video")
    public ResponseEntity<GetVideoDto> assignVideoToCourse(
            @RequestBody @Valid AssignVideoToCourseDto dto
    ) throws NotFoundException {
        Video video = videoService.assignVideoToCourse(dto);
        GetVideoDto responseDto = courseMapper.mapVideoToGetVideoDto(video);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PutMapping("video/{id}")
    public ResponseEntity<GetVideoDto> updateVideoOfCourseById(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateVideoDetailDto dto
    ) throws NotFoundException {
        Video video = videoService.updateVideoById(id, dto);
        GetVideoDto responseDto = courseMapper.mapVideoToGetVideoDto(video);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("video/{id}")
    public ResponseEntity<GetVideoDto> deleteVideoOfCourseById(
            @PathVariable("id") Long id
    ) throws NotFoundException {
        Video video = videoService.deleteVideoById(id);
        GetVideoDto responseDto = courseMapper.mapVideoToGetVideoDto(video);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<GetCourseDto> createCourse(
            @RequestBody @Valid CreateCourseDto dto
    ) throws DuplicateException, NotFoundException {
        Course course = courseService.createCourse(dto);
        GetCourseDto responseDto = courseMapper.mapCourseToGetCourseDto(course);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<GetCourseDto> deleteCourseById(
            @PathVariable("id") Long id
    ) throws NotFoundException {
        Course course = courseService.deleteCourseById(id);
        GetCourseDto responseDto = courseMapper.mapCourseToGetCourseDto(course);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<GetCourseDto>> getCourses() {
        List<Course> courses = courseService.getCourses();
        List<GetCourseDto> responseDto = courseMapper.mapCoursesToListOfGetCourseDto(courses);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
