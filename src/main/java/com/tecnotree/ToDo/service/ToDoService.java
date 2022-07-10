package com.tecnotree.ToDo.service;

import com.tecnotree.ToDo.dto.ToDoDto;

import java.util.List;

/**
 * The interface ToDoService all method todos.
 */
public interface ToDoService {

    List<ToDoDto> findAllToDo();

    List<ToDoDto> findUserByCompleted(Long userId, boolean completed);
}
