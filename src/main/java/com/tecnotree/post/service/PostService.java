package com.tecnotree.post.service;

import com.tecnotree.post.dto.PostDto;
import com.tecnotree.post.dto.PostRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * The interface PostService all method post.
 */
public interface PostService {

    Page<PostDto> findAllPosts(int page, int size);

    PostDto findPostById(Long PostId);

    List<PostDto> findPostByTitle(String title);

    PostDto createPosts(PostRequestDto postRequestDto);

    PostDto updatePostById(Long postId, PostRequestDto postRequestDto);

    void deleteByPostId(Long postId);

}
