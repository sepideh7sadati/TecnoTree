package com.tecnotree.client;

import com.tecnotree.client.dtocilent.CommentClientDto;
import com.tecnotree.client.dtocilent.PostClientDto;
import com.tecnotree.client.dtocilent.TodosClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * The interface ApiClient call json.
 */
@FeignClient(value = "placeholder", url = "https://jsonplaceholder.typicode.com")
public interface ApiClient {

    @GetMapping("posts")
    List<PostClientDto> getAllPosts();

    @GetMapping("/comments")
    List<CommentClientDto> getAllComments();

    @GetMapping("/todos")
    List<TodosClientDto> getAllTodos();
}
