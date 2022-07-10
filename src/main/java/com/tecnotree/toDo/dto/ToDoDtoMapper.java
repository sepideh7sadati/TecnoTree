package com.tecnotree.toDo.dto;


import com.tecnotree.entities.ToDo;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The interface ToDoDtoMapper convert entity to dto .
 */
@Mapper(componentModel = "spring")
public interface ToDoDtoMapper {
    List<ToDoDto> map (List<ToDo> toDoList);
}
