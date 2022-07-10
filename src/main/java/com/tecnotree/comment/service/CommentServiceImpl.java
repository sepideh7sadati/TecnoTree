package com.tecnotree.comment.service;

import com.tecnotree.comment.dto.CommentDto;
import com.tecnotree.comment.dto.CommentDtoMapper;
import com.tecnotree.comment.dto.CommentRequestDto;
import com.tecnotree.comment.reopsitory.CommentRepository;
import com.tecnotree.entities.Comment;
import com.tecnotree.exception.CommentException;
import com.tecnotree.exception.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class CommentServiceImpl find, update, create, delete comment.
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoMapper commentDtoMapper;

    public CommentServiceImpl(CommentRepository commentRepository, CommentDtoMapper commentDtoMapper) {
        this.commentRepository = commentRepository;
        this.commentDtoMapper = commentDtoMapper;
    }
    /**
     * this method findCommentsListByPostId: get all comment with postId
     *
     * @param Long postId
     * @return  list CommentDto
     *
     */
    @Override
    public List<CommentDto> findCommentsListByPostId(Long postId) {
        List<Comment> comment = commentRepository.findByPostId(postId);
        log.info("postId is :({}) ", postId);
        return commentDtoMapper.map(comment);
    }

    /**
     * this method findAllComments: get all comment with page and size
     *
     * @param int page, int size
     * @return  list CommentDto
     *
     */
    @Override
    public Page<CommentDto> findAllComments(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return commentRepository.findAll(pageRequest).map(commentDtoMapper::map);
    }

    /**
     * this method createComment: create comment with input CommentRequestDto
     *
     * @param  CommentRequestDto commentRequestDto
     * @return  CommentDto
     *
     */
    @Override
    public CommentDto createComment(CommentRequestDto commentRequestDto) {
        Comment comment = commentDtoMapper.map(commentRequestDto);
        Comment createComment =  commentRepository.save(comment);
        log.info("create comment with id :({})", createComment.getId());
        return commentDtoMapper.map(createComment);
    }

    /**
     * this method updateCommentById: update comment with input commentId and CommentRequestDto
     *
     * @param  Long commentId, CommentRequestDto commentRequestDto
     * @return  CommentDto
     *
     */
    @Override
    public CommentDto updateCommentById(Long commentId, CommentRequestDto commentRequestDto) {
        getCommentId(commentId);
        log.info("commentId is :({})", commentId);
        Comment updateComment = commentDtoMapper.map(commentRequestDto);
        Comment comment = commentRepository.save(updateComment);
        log.info("update comment with id :({})", comment.getId());
        return commentDtoMapper.map(comment);
    }

    /**
     * this method deleteByCommentId: delete comment with input commentId
     *
     * @param  Long commentId
     *
     */
    @Override
    public void deleteByCommentId(Long commentId) {
        getCommentId(commentId);
        log.info("comment id :({})", commentId);
        commentRepository.deleteById(commentId);
        log.info("deleted comment with id :({})", commentId);
    }

    /**
     * @throws CommentException  If an input exception occurred
     *
     */
    public Comment getCommentId(Long commentId) {
       return commentRepository.findById(commentId).orElseThrow(() -> new CommentException(Message.COMMENT_NOT_FOUND));
    }
}
