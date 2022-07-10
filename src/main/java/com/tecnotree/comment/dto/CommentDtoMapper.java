package com.tecnotree.comment.dto;

import com.tecnotree.comment.entity.Comment;
import org.mapstruct.Mapper;

import java.util.List;


/**
 * The interface CommentDtoMapper convert entity to dto .
 */
@Mapper(componentModel = "spring")
public interface CommentDtoMapper {
    List<CommentDto> map(List<Comment> dtos);
    CommentDto map(Comment comment);
    Comment map (CommentRequestDto commentRequestDto);
}
