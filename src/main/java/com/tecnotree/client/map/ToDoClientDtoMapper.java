package com.tecnotree.client.map;

import com.tecnotree.client.dtocilent.TodosClientDto;
import com.tecnotree.toDo.entity.ToDo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The Class CommentDtoMapper convert json.
 */
@Mapper(componentModel = "spring")
public interface ToDoClientDtoMapper {
    List<ToDo> map (List<TodosClientDto> dtos);
}
