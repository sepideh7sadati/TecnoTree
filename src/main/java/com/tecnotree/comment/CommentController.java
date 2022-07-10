package com.tecnotree.comment;

import com.tecnotree.comment.dto.CommentDto;
import com.tecnotree.comment.dto.CommentRequestDto;
import com.tecnotree.comment.service.CommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class CommentController post apis.
 */
@RestController
@RequestMapping("comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "Get comments all with size and page", response = CommentDto.class)
    @GetMapping
    public ResponseEntity<Page<CommentDto>> getCommentsAll(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ) {
        return ResponseEntity.ok(commentService.findAllComments(page, size));
    }

    @ApiOperation(value = "Get comment with postId", response = CommentDto.class)
    @GetMapping("specific-post/")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(commentService.findCommentsListByPostId(postId));
    }

    @ApiOperation(value = "create comment", response = CommentDto.class)
    @PostMapping()
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.createComment(commentRequestDto));
    }

    @ApiOperation(value = "Update comment with commentId", response = CommentDto.class)
    @PatchMapping("{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "commentId") Long commentId,
                                                    @RequestBody CommentRequestDto commentRequestDto) {
        return ResponseEntity.ok(commentService.updateCommentById(commentId, commentRequestDto));
    }

    @ApiOperation(value = "Delete comment with commentId", response = CommentDto.class)
    @DeleteMapping("{commentId}")
    public ResponseEntity deleteComment(@PathVariable(value = "commentId") Long commentId) {
        commentService.deleteByCommentId(commentId);
        return ResponseEntity.ok().build();
    }


}
