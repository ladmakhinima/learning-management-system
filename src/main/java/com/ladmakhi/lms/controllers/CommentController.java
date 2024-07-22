package com.ladmakhi.lms.controllers;

import com.ladmakhi.lms.common.annotation.GetCurrentUser;
import com.ladmakhi.lms.common.exception.NotFoundException;
import com.ladmakhi.lms.dtos.comment.CreateCommentDto;
import com.ladmakhi.lms.dtos.comment.GetCommentDto;
import com.ladmakhi.lms.mappers.CommentMapper;
import com.ladmakhi.lms.models.Comment;
import com.ladmakhi.lms.models.User;
import com.ladmakhi.lms.services.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    @PostMapping
    public ResponseEntity<GetCommentDto> createComment(
            @RequestBody @Valid CreateCommentDto dto,
            @GetCurrentUser User user
    ) throws NotFoundException {
        Comment comment = commentService.createComment(dto, user);
        GetCommentDto responseDto = commentMapper.mapCommentToGetCommentDto(comment);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<GetCommentDto> deleteCommentById(
            @PathVariable("id") Long id
    ) throws NotFoundException {
        Comment comment = commentService.deleteCommentById(id);
        GetCommentDto responseDto = commentMapper.mapCommentToGetCommentDto(comment);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("{courseId}/courses")
    public ResponseEntity<List<GetCommentDto>> getCommentsByCourseId(
            @PathVariable("courseId") Long courseId
    ) {
        List<Comment> comments = commentService.findCommentByCourseId(courseId);
        List<GetCommentDto> responseDto = commentMapper.mapCommentsToListOfGetCommentDto(comments);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
