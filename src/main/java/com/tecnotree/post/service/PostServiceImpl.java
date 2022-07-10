package com.tecnotree.post.service;

import com.tecnotree.entities.Post;
import com.tecnotree.exception.Message;
import com.tecnotree.exception.PostException;
import com.tecnotree.post.dto.PostDto;
import com.tecnotree.post.dto.PostDtoMapper;
import com.tecnotree.post.dto.PostRequestDto;
import com.tecnotree.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class PostServiceImpl find, update, create,delete posts.
 */
@Service
@Slf4j
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostDtoMapper postDtoMapper;

    public PostServiceImpl(PostRepository postRepository, PostDtoMapper postDtoMapper) {
        this.postRepository = postRepository;
        this.postDtoMapper = postDtoMapper;
    }

    /**
     * this method findAllPosts: get all posts with page and size
     *
     * @param int page , int size
     * @return  page of postDto class
     *
     */
    @Override
    public Page<PostDto> findAllPosts(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id"));
        return postRepository.findAll(pageRequest).map(postDtoMapper::map);
    }

    /**
     * this method findPostById: return post based on postId
     *
     * @param Long postId
     * @return  page of postDto class
     *
     */
    @Override
    public PostDto findPostById(Long postId) {
        Post post = getPostById(postId);
        log.info("postId is :({}) ", postId);
        return postDtoMapper.map(post);
    }

    /**
     * this method findPostByTitle: return post based on title
     *
     * @param  String title
     * @return  page of postDto class
     *
     */
    @Override
    public List<PostDto> findPostByTitle(String title) {
        List<Post> postListTitle = postRepository.findPostByTitle(title);
        log.info("title is :({}) ", title);
        return postDtoMapper.map(postListTitle);
    }

    /**
     * this method createPosts: create post based on postRequestDto class
     *
     * @param  PostRequestDto postRequestDto
     * @return  page of postDto class
     *
     */
    @Override
    public PostDto createPosts(PostRequestDto postRequestDto) {
        Post post = postDtoMapper.map(postRequestDto);
        Post createPost = postRepository.save(post);
        log.info("create post with by id : ({})", createPost.getId());
        return postDtoMapper.map(createPost);
    }

    /**
     * this method updatePostById: update post based on postId and postRequestDto class
     * if the method didn't find postId we get exception
     *
     * @param  PostRequestDto postRequestDto
     * @return  page of postDto class
     *
     */
    @Override
    public PostDto updatePostById(Long postId, PostRequestDto postRequestDto) {
        getPostById(postId);
        log.info("postId is :({}) ", postId);
        Post updatePost = postDtoMapper.map(postRequestDto);
        Post postUpdate = postRepository.save(updatePost);
        log.info("update post with by id : ({})", postUpdate.getId());
        return postDtoMapper.map(postUpdate);
    }

    /**
     * this method deleteByPostId: delete post based on postId
     * if the method didn't find postId we get exception
     *
     * @param  Long postId
     *
     */
    @Override
    public void deleteByPostId(Long postId) {
        getPostById(postId);
        log.info("postId is :({}) ", postId);
        postRepository.deleteById(postId);
        log.info("deleted post with by id : ({})", postId);
    }

    /**
     * @throws PostException  If an input exception occurred
     *
     */
    public Post getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new PostException(Message.POST_NOT_FOUND));
        log.info("postId is :({}) ", postId);
        return post;
    }
}
