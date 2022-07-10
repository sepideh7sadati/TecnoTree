package com.tecnotree.placeholder.todo;


import com.tecnotree.ToDo.dto.ToDoDto;
import com.tecnotree.ToDo.dto.ToDoDtoMapper;
import com.tecnotree.ToDo.repository.ToDoRepository;
import com.tecnotree.ToDo.service.ToDoServiceImpl;
import com.tecnotree.entities.ToDo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * The class ToDoServiceTest service todos.
 */
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ToDoServiceTest {

    @Mock
    private ToDoRepository toDoRepository;
    @Mock
    private ToDoDtoMapper toDoDtoMapper;
    @InjectMocks
    private ToDoServiceImpl toDoService;


    @Test
    public void success_when_find_all_to_do_then_get_all_toDos() {
        //given
        Long userId = 14l;
        Long id = 2l;
        String title = "toDoTitle";
        boolean completed = true;

       List <ToDoDto> toDoDtoList = Arrays.asList(ToDoDto.builder()
                .id(id)
                .userId(userId)
                .title(title)
                .completed(completed).build());

       List<ToDo> toDoList = Arrays.asList(new  ToDo(id, userId, title, completed));

       when(toDoRepository.findAll()).thenReturn(toDoList);
       when(toDoDtoMapper.map(toDoList)).thenReturn(toDoDtoList);

       //when
        List<ToDoDto> allToDo = toDoService.findAllToDo();

        //then
        Assertions.assertEquals(allToDo.size(), 1);
        Assertions.assertEquals(allToDo.get(0).getId(), 2);
        Assertions.assertEquals(allToDo.get(0).getUserId(), 14);
        Assertions.assertEquals(allToDo.get(0).getTitle(), "toDoTitle");

    }

    @Test
    public void success_give_userId_and_completed_when_find_to_do_then_get_toDoDto_list() {
       //given
        Long userId = 14l;
        Long id = 2l;
        String title = "toDoTitleFind";
        boolean completed = true;

        List <ToDoDto> toDoDtoList = Arrays.asList(ToDoDto.builder()
                .id(id)
                .userId(userId)
                .title(title)
                .completed(completed).build());

        List<ToDo> toDoList = Arrays.asList(new  ToDo(id, userId, title, completed));

        when(toDoRepository.findByUserIdAndCompleted(anyLong(), anyBoolean())).thenReturn(toDoList);
        when(toDoDtoMapper.map(toDoList)).thenReturn(toDoDtoList);

        //when
        List<ToDoDto> userByCompleted = toDoService.findUserByCompleted(userId, completed);

        //then
        Assertions.assertEquals(userByCompleted.get(0).getId(), 2);
        Assertions.assertEquals(userByCompleted.get(0).getUserId(), 14);
        Assertions.assertEquals(userByCompleted.get(0).getTitle(), "toDoTitleFind");

    }
}
