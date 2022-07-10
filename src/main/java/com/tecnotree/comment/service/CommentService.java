package com.tecnotree.comment.service;

import com.tecnotree.comment.dto.CommentDto;
import com.tecnotree.comment.dto.CommentRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * The interface CommentService all method post.
 */
public interface CommentService {
    List<CommentDto> findCommentsListByPostId(Long postId);

    Page<CommentDto> findAllComments(int page, int size);

    CommentDto createComment(CommentRequestDto commentRequestDto);

    CommentDto updateCommentById(Long commentId, CommentRequestDto commentRequestDto);

    void deleteByCommentId(Long commentId);
}
