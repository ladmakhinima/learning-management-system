package com.ladmakhi.lms.mappers;

import com.ladmakhi.lms.dtos.course.GetCourseDto;
import com.ladmakhi.lms.dtos.course.GetVideoDto;
import com.ladmakhi.lms.models.Course;
import com.ladmakhi.lms.models.Video;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {
    GetVideoDto mapVideoToGetVideoDto(Video video);

    GetCourseDto mapCourseToGetCourseDto(Course course);

    List<GetCourseDto> mapCoursesToListOfGetCourseDto(List<Course> courses);
}
