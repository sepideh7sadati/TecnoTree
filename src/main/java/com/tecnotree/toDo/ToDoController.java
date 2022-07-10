package com.tecnotree.toDo;


import com.tecnotree.toDo.dto.ToDoDto;
import com.tecnotree.toDo.service.ToDoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The class ToDoController todos apis.
 */
@RestController
@RequestMapping("toDo")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @ApiOperation(value = "Get todos all", response = ToDoDto.class)
    @GetMapping
    public ResponseEntity<List<ToDoDto>> getToDosAll() {
        return ResponseEntity.ok(toDoService.findAllToDo());
    }

    @ApiOperation(value = "Get all posts with title", response = ToDoDto.class)
    @GetMapping("user")
    public ResponseEntity<List<ToDoDto>> getAllPostsByTitle(@RequestParam(value = "userId") Long userId,
                                                            @RequestParam(value = "completed") boolean completed) {
        return ResponseEntity.ok(toDoService.findUserByCompleted(userId, completed));
    }
}
