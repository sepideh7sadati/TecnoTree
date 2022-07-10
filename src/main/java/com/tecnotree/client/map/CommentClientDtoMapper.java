package com.tecnotree.client.map;

import com.tecnotree.client.dtocilent.CommentClientDto;
import com.tecnotree.entities.Comment;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The Class CommentDtoMapper convert json.
 */
@Mapper(componentModel = "spring")
public interface CommentClientDtoMapper {
    List<Comment> map (List<CommentClientDto> dtos);
}
