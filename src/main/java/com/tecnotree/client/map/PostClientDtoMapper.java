package com.tecnotree.client.map;

import com.tecnotree.client.dtocilent.PostClientDto;
import com.tecnotree.entities.Post;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The Class CommentDtoMapper convert json.
 */
@Mapper(componentModel = "spring")
public interface PostClientDtoMapper {
    List<Post> map (List<PostClientDto> dtos);
}
