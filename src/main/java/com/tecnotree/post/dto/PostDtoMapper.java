package com.tecnotree.post.dto;

import com.tecnotree.post.entity.Post;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The interface PostDtoMapper convert entity to dto .
 */
@Mapper(componentModel = "spring")
public interface PostDtoMapper {
    PostDto map(Post post);
    List<PostDto> map (List<Post> posts);
    Post map (PostRequestDto postRequestDto);
}
