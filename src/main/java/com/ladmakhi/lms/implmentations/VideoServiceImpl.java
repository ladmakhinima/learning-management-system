package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.course.AssignVideoToCourseDto;
import com.ladmakhi.lms.dtos.course.UpdateVideoDetailDto;
import com.ladmakhi.lms.models.Course;
import com.ladmakhi.lms.models.Video;
import com.ladmakhi.lms.repositories.VideoRepository;
import com.ladmakhi.lms.services.CourseService;
import com.ladmakhi.lms.services.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VideoServiceImpl implements VideoService {
    private final CourseService courseService;
    private final VideoRepository videoRepository;

    @Override
    public Video assignVideoToCourse(AssignVideoToCourseDto dto) throws NotFoundException {
        Course course = courseService.findCourseById(dto.getCourseId());
        return videoRepository.save(
                Video.builder()
                        .videoUrl(dto.getVideoUrl())
                        .description(dto.getDescription())
                        .title(dto.getTitle())
                        .course(course)
                        .build()
        );
    }

    @Override
    public Video updateVideoById(Long id, UpdateVideoDetailDto dto) throws NotFoundException {
        Video video = findVideoById(id);
        video.setTitle(dto.getTitle());
        video.setDescription(dto.getDescription());
        return videoRepository.save(video);
    }

    @Override
    public Video findVideoById(Long id) throws NotFoundException {
        return videoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("ویدیو مورد نظر یافت نشد"));
    }

    @Override
    public Video deleteVideoById(Long id) throws NotFoundException {
        Video video = findVideoById(id);
        videoRepository.delete(video);
        return video;
    }
}
