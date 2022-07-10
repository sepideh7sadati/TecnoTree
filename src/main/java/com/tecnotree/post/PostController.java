package com.tecnotree.post;

import com.tecnotree.comment.dto.CommentDto;
import com.tecnotree.comment.service.CommentService;
import com.tecnotree.post.dto.PostDto;
import com.tecnotree.post.dto.PostRequestDto;
import com.tecnotree.post.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The class PostController posts apis.
 */
@RestController
@RequestMapping("posts")
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @ApiOperation(value = "Get posts all with size and page", response = PostDto.class)
    @GetMapping
    public ResponseEntity<Page<PostDto>> getPostsAll(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ) {
        return ResponseEntity.ok(postService.findAllPosts(page, size));
    }

    @ApiOperation(value = "Get posts with postId", response = PostDto.class)
    @GetMapping("{postId}")
    public ResponseEntity<PostDto> getPostsById(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(postService.findPostById(postId));
    }

    @ApiOperation(value = "Get comments with postId", response = CommentDto.class)
    @GetMapping("{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable(value = "postId") Long postId) {
        return ResponseEntity.ok(commentService.findCommentsListByPostId(postId));
    }


    @ApiOperation(value = "Get all posts with title", response = PostDto.class)
    @GetMapping("titlePost")
    public ResponseEntity<List<PostDto>> getAllPostsByTitle(@RequestParam(value = "title") String title) {
        return ResponseEntity.ok(postService.findPostByTitle(title));
    }

    @ApiOperation(value = "Create post", response = PostDto.class)
    @PostMapping()
    public ResponseEntity<PostDto> createPost(@RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.createPosts(postRequestDto));
    }

    @ApiOperation(value = "Update post with postId", response = PostDto.class)
    @PatchMapping("{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable(value = "postId") Long postId,
                                              @RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.updatePostById(postId, postRequestDto));
    }

    @ApiOperation(value = "Delete post with postId")
    @DeleteMapping("{postId}")
    public ResponseEntity deletePost(@PathVariable(value = "postId") Long postId) {
        postService.deleteByPostId(postId);
        return ResponseEntity.ok().build();
    }

}
