package com.tecnotree.toDo.service;

import com.tecnotree.toDo.dto.ToDoDto;
import com.tecnotree.toDo.dto.ToDoDtoMapper;
import com.tecnotree.toDo.repository.ToDoRepository;
import com.tecnotree.entities.ToDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class ToDoServiceImpl find, create  todoImpl.
 */
@Service
@Slf4j
public class ToDoServiceImpl implements ToDoService{

    private final ToDoRepository toDoRepository;
    private final ToDoDtoMapper toDoDtoMapper;

    public ToDoServiceImpl(ToDoRepository toDoRepository, ToDoDtoMapper toDoDtoMapper) {
        this.toDoRepository = toDoRepository;
        this.toDoDtoMapper = toDoDtoMapper;
    }

    /**
     * this method findAllToDo: get all ToDoDto
     *
     * @return  list ToDoDto
     *
     */
    @Override
    public List<ToDoDto> findAllToDo() {
        List<ToDo> toDoList = toDoRepository.findAll();
        log.info("toDoList :[{}]", toDoList);
        return toDoDtoMapper.map(toDoList);
    }

    /**
     * this method findUserByCompleted: find Todomethod with Long userId, boolean completed
     *
     * @param Long userId, boolean completed
     * @return  list ToDoDto
     *
     */
    @Override
    public List<ToDoDto> findUserByCompleted(Long userId, boolean completed) {
      List<ToDo> toDoList = toDoRepository.findByUserIdAndCompleted(userId, completed);
      log.info("userid ({}) and completed ({}) are", userId, completed);
        return toDoDtoMapper.map(toDoList);
    }
}
