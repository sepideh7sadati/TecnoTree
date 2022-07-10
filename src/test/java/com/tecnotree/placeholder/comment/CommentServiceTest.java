package com.tecnotree.placeholder.comment;

import com.tecnotree.comment.dto.CommentDto;
import com.tecnotree.comment.dto.CommentDtoMapper;
import com.tecnotree.comment.dto.CommentRequestDto;
import com.tecnotree.comment.reopsitory.CommentRepository;
import com.tecnotree.comment.service.CommentServiceImpl;
import com.tecnotree.comment.entity.Comment;
import com.tecnotree.exception.CommentException;
import com.tecnotree.exception.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

/**
 * The class CommentServiceTest service comment.
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private CommentDtoMapper commentDtoMapper;
    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    public void success_given_page_when_find_all_then_get_all_comments() {

        //given
        Long id = 3l;
        Long postId = 1l;
        String name = "commentName";
        String email = "comment@gmail.com";
        String body = "commentBody";
        int page = 1;
        int size = 2;
        Comment comment = new Comment(id, postId, name, email, body, null);
        List<Comment> comments = Arrays.asList(comment);
        PageRequest pageable = PageRequest.of(page, size);
        Page<Comment> commentPage = new PageImpl<>(comments, pageable, 10);

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setPostId(comment.getPostId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        when(commentRepository.findAll(any(PageRequest.class))).thenReturn(commentPage);
        when(commentDtoMapper.map(comment)).thenReturn(commentDto);

        //when
        Page<CommentDto> allComments = commentService.findAllComments(1, 10);
        //then
        Assertions.assertEquals(allComments.getTotalElements(), 10);
    }

    @Test
    public void success_given_postId_when_find_post_id_then_comments_list() {
        //given
        Long firstId = 4l;
        Long secondId = 4l;
        Long postId = 1l;
        String firstName = "firstName";
        String secondName = "secondName";
        String email = "@gmail.com";
        String body = "commentBody";
        List<Comment> commentList = Arrays.asList(
                new Comment(firstId, postId, firstName, email, body, null),
                new Comment(secondId, postId, secondName, email, body, null)
        );

        List<CommentDto> commentDtoList = Arrays.asList(
                new CommentDto(postId, firstId, firstName, email, body)
        );
        when(commentRepository.findByPostId(anyLong())).thenReturn(commentList);
        when(commentDtoMapper.map(commentList)).thenReturn(commentDtoList);
        //when
        List<CommentDto> commentsListByPostId = commentService.findCommentsListByPostId(postId);

        //then
        Assertions.assertEquals(commentsListByPostId.size(), 1);
        Assertions.assertEquals(commentsListByPostId.get(0).getId(), 4);
        Assertions.assertEquals(commentsListByPostId.get(0).getPostId(), 1);
        Assertions.assertEquals(commentsListByPostId.get(0).getBody(), "commentBody");
        Assertions.assertEquals(commentsListByPostId.get(0).getBody(), "commentBody");
        Assertions.assertEquals(commentsListByPostId.get(0).getName(), "firstName");

    }

    @Test
    public void success_given_comment_request_when_request_save_then_create_comment() {
        //given
        Long postId = 4l;
        Long id = 2l;
        String name = "comment";
        String email = "sepid@gmail.com";
        String body = "body";
        Long commentId = 3l;

        Comment comment = new Comment(commentId, postId, name, email, body, null);
        CommentRequestDto commentRequestDto = new CommentRequestDto(postId, id, name, email, body);
        CommentDto commentDto = new CommentDto(comment.getId(), comment.getPostId(), comment.getName()
                , comment.getEmail(), comment.getBody());
        lenient().when(commentDtoMapper.map(commentRequestDto)).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        lenient().when(commentDtoMapper.map(comment)).thenReturn(commentDto);

        //when
        CommentDto createComment = commentService.createComment(commentRequestDto);

        //then
        Assertions.assertEquals(createComment.getId(), 4);
        Assertions.assertEquals(createComment.getPostId(), 3);
        Assertions.assertEquals(createComment.getName(), "comment");
        Assertions.assertEquals(createComment.getBody(), body);
        Assertions.assertEquals(createComment.getEmail(), "sepid@gmail.com");

    }

    @Test
    public void success_given_comment_id_when_map_comment_Request_then_update_comment() {
        //given
        Long postId = 8l;
        Long id = 2l;
        String name = "RequestComment";
        String email = "tecnoTree@gmail.com";
        String updateEmail = "update@gmail.com";
        String body = "body";
        Long commentId = 1l;
        CommentRequestDto commentRequestDto = new CommentRequestDto(postId, id, name, email, body);
        Comment comment = new Comment(commentId, postId, name, email, body, null);
        CommentDto commentDto = new CommentDto(comment.getId(), comment.getPostId(), comment.getName()
                , updateEmail, comment.getBody());


        when(commentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));
        lenient().when(commentDtoMapper.map(commentRequestDto)).thenReturn(comment);
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
        lenient().when(commentDtoMapper.map(comment)).thenReturn(commentDto);

        //when
        CommentDto updateComment = commentService.updateCommentById(commentId, commentRequestDto);

        //then
        Assertions.assertEquals(updateComment.getId(), 8);
        Assertions.assertEquals(updateComment.getPostId(), 1);
        Assertions.assertEquals(updateComment.getName(), "RequestComment");
        Assertions.assertEquals(updateComment.getBody(), body);
        Assertions.assertEquals(updateComment.getEmail(), "update@gmail.com");

    }

    @Test
    public void success_given_comment_id_when_call_delete_method_then_deleted_id() {
        //given
        Long id = 5l;
        Long postId = 1l;
        String name = "name";
        String email = "@yahoo.com";
        String body = "bodyDelete";

        Comment comment = new Comment(id, postId, name, email, body, null);
        when(commentRepository.findById(anyLong())).thenReturn(Optional.ofNullable(comment));

        //when
        commentService.deleteByCommentId(postId);
    }

    @Test
    public void fail_given_comment_id_then_not_found_id_when_throw_exception() {
        Long commentId = 5l;

        when(commentRepository.findById(commentId)).thenThrow(new CommentException(Message.COMMENT_NOT_FOUND));
        try {
            commentService.getCommentId(commentId);
        }catch (Throwable e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals(e.getMessage(), "comment not found");
        }
    }
}
