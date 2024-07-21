package com.ladmakhi.lms.implmentations;

import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.comment.CreateCommentDto;
import com.ladmakhi.lms.models.Comment;
import com.ladmakhi.lms.models.Course;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.models.UserRole;
import com.ladmakhi.lms.repositories.CommentRepository;
import com.ladmakhi.lms.services.CommentService;
import com.ladmakhi.lms.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CourseService courseService;

    @Override
    public Comment createComment(CreateCommentDto dto, User author) throws NotFoundException {
        Course course = courseService.findCourseById(dto.getCourseId());

        var commentBuilder = Comment.builder()
                .author(author)
                .content(dto.getContent())
                .course(course);
        if (dto.getParentId() != null) {
            commentBuilder.parent(
                    findCommentById(dto.getParentId())
            );
        }
        return commentRepository.save(
                commentBuilder.build()
        );
    }

    @Override
    public Comment deleteCommentById(Long id) throws NotFoundException {
        Comment comment = findCommentById(id);
        commentRepository.delete(comment);
        return comment;
    }

    @Override
    public Comment findCommentById(Long id) throws NotFoundException {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("کامنت مورد نظر یافت نشد"));
    }
}
