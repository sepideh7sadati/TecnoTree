package com.tecnotree.ToDo;


import com.tecnotree.ToDo.dto.ToDoDto;
import com.tecnotree.ToDo.service.ToDoService;
import com.tecnotree.comment.dto.CommentDto;
import com.tecnotree.post.dto.PostDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
