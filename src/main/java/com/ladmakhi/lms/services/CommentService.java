package com.ladmakhi.lms.services;

import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.comment.CreateCommentDto;
import com.ladmakhi.lms.models.Comment;
import com.ladmakhi.lms.models.User;

import java.util.List;

public interface CommentService {
    Comment createComment(CreateCommentDto dto, User author) throws NotFoundException;

    Comment deleteCommentById(Long id) throws NotFoundException;

    Comment findCommentById(Long id) throws NotFoundException;

    List<Comment> findCommentByCourseId(Long id);
}
