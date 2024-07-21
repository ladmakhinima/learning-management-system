package com.ladmakhi.lms.services;

import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.course.AssignVideoToCourseDto;
import com.ladmakhi.lms.dtos.course.UpdateVideoDetailDto;
import com.ladmakhi.lms.models.Video;

public interface VideoService {
    Video assignVideoToCourse(AssignVideoToCourseDto dto) throws NotFoundException;
    Video updateVideoById(Long id, UpdateVideoDetailDto dto) throws NotFoundException;
    Video findVideoById(Long id) throws NotFoundException;
    Video deleteVideoById(Long id) throws NotFoundException;
}
