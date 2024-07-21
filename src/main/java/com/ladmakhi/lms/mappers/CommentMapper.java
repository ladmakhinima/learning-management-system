package com.ladmakhi.lms.mappers;

import com.ladmakhi.lms.dtos.comment.GetCommentDto;
import com.ladmakhi.lms.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {
    GetCommentDto mapCommentToGetCommentDto(Comment comment);
}
