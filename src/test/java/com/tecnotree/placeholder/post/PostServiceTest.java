package com.tecnotree.placeholder.post;


import com.tecnotree.entities.Post;
import com.tecnotree.exception.Message;
import com.tecnotree.exception.PostException;
import com.tecnotree.post.dto.PostDto;
import com.tecnotree.post.dto.PostDtoMapper;
import com.tecnotree.post.dto.PostRequestDto;
import com.tecnotree.post.repository.PostRepository;
import com.tecnotree.post.service.PostServiceImpl;
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

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;


/**
 * The class PostServiceTest service post.
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private PostDtoMapper postDtoMapper;

    @InjectMocks
    private PostServiceImpl postService;

    @Test
    public void success_given_page_when_find_all_then_get_all_posts() {
        //given
        Long id = 1l;
        Long userId = 1l ;
        String title = "title";
        String body = "body";
        int page = 1;
        int size = 2 ;
        Post post = new Post(id, userId, title, body, null);
        List<Post> posts = Arrays.asList(post);
        PageRequest pageable = PageRequest.of(page, size);
        Page<Post> postPage = new PageImpl<>(posts, pageable, 10);

        PostDto maps = new PostDto();
        maps.setId(post.getId());
        maps.setUserId(post.getUserId());
        maps.setTitle(post.getTitle());
        maps.setBody(post.getBody());

        when(postRepository.findAll(any(PageRequest.class))).thenReturn(postPage);
        when(postDtoMapper.map(post)).thenReturn(maps);

        //when
        Page<PostDto> allPosts = postService.findAllPosts(1, 10);
        //then
        Assertions.assertEquals(allPosts.getTotalElements(), 10);
    }

    @Test
    public void success_given_ids_then_find_when_should_return_postDto() {
        //given

        Long id = 2l;
        Long userId = 1l;
        String title = "title";
        String body = "body";

        Post post = new Post(id, userId, title, body, null);

        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setUserId(post.getUserId());
        postDto.setTitle(post.getTitle());
        postDto.setBody(post.getBody());

        when(postRepository.findById(anyLong())).thenReturn(Optional.ofNullable(post));
        when(postDtoMapper.map(post)).thenReturn(postDto);

        //when
        postService.findPostById(id);

        //then
        Assertions.assertEquals(postDto.getId(), id);
        Assertions.assertEquals(postDto.getUserId(), userId);
        Assertions.assertEquals(postDto.getTitle(), title);
        Assertions.assertEquals(postDto.getBody(), body);
    }

    @Test
    public void success_given_title_then_find_when_should_return_List_postDto() {

        //given
        Long firstId = 3l;
        Long secondId = 4l;
        Long userId = 1l;
        String title = "title";
        String firstTitle = "first title";
        String secondTitle = "second title";
        String firstBody = "first body";
        String secondBody = "second body";


        List<Post> postList = Arrays.asList(
                new Post(firstId, userId, firstTitle, firstBody, null),
                new Post(secondId, userId, secondTitle, secondBody, null));


        List<PostDto> postDto = Arrays.asList(
                new PostDto(userId, firstId, firstTitle, firstBody),
                new PostDto(userId, secondId, secondTitle, secondBody));

        when(postRepository.findPostByTitle(anyString())).thenReturn(postList);
        when(postDtoMapper.map(postList)).thenReturn(postDto);

        //when
        List<PostDto> postByTitle = postService.findPostByTitle(title);

        //then
        Assertions.assertEquals(postByTitle.size(), 2);
    }

    @Test
    public void success_given_request_dto_then_create_post_when_should_return_postDto() {
        //given
        Long id = 5l;
        Long userId = 1l;
        String title = "title";
        String body = "body";

        PostRequestDto postRequestDto = new PostRequestDto(userId, title, body);
        Post post = new Post(id, userId, title, body, null);
        PostDto postDto = new PostDto(userId, id, title, body);

        lenient().when(postDtoMapper.map(any(PostRequestDto.class))).thenReturn(post);
        when(postRepository.save(any(Post.class))).thenReturn(post);
        lenient().when(postDtoMapper.map(any(Post.class))).thenReturn(postDto);

        //when
        PostDto postResult = postService.createPosts(postRequestDto);

        //then
        Assertions.assertEquals(postResult.getId(), id);
        Assertions.assertEquals(postResult.getUserId(), userId);
        Assertions.assertEquals(postResult.getTitle(), title);
        Assertions.assertEquals(postResult.getBody(), body);

    }

    @Test
    public void fail_given_post_id_then_not_found_id_when_throw_exception() {
        Long postId = 5l;

            when(postRepository.findById(postId)).thenThrow(new PostException(Message.POST_NOT_FOUND));
        try {
            postService.getPostById(postId);
        }catch (Throwable e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals(e.getMessage(), "post not found");
        }
    }

    @Test
    public void success_given_request_dto_then_update_post_when_should_return_postDto() {
        //given
        Long postId = 5l;
        Long userId = 1l;
        String title = "title";
        String body = "body";
        Post post = new Post(postId, userId, title, body, null);

        Long requestUserId = 2l;
        String requestTitle = "updateTitle";
        String requestBody = "updateBody";
        PostDto postDto = new PostDto(postId, requestUserId, requestTitle, requestBody);
        PostRequestDto postRequestDto = new PostRequestDto(requestUserId, requestTitle, requestBody);


        when(postRepository.findById(anyLong())).thenReturn(Optional.ofNullable(post));
        lenient().when(postDtoMapper.map(any(PostRequestDto.class))).thenReturn(post);
        when(postRepository.save(any(Post.class))).thenReturn(post);
        lenient().when(postDtoMapper.map(any(Post.class))).thenReturn(postDto);
        //when
        PostDto postDtoResult = postService.updatePostById(postId, postRequestDto);

        //then
        Assertions.assertEquals(postDtoResult.getId(), 2);
        Assertions.assertEquals(postDtoResult.getUserId(), 5);
        Assertions.assertEquals(postDtoResult.getTitle(), requestTitle);
        Assertions.assertEquals(postDtoResult.getBody(), requestBody);

    }

    @Test
    public void success_given_post_id_when_call_delete_method_then_deleted_id() {
        //given
        Long postId = 5l;
        Long userId = 1l;
        String title = "titleDelete";
        String body = "bodyDelete";
        Post post = new Post(postId, userId, title, body, null);

        when(postRepository.findById(anyLong())).thenReturn(Optional.ofNullable(post));

        //when
        postService.deleteByPostId(postId);

    }

}
