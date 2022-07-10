package com.tecnotree;

import com.tecnotree.ToDo.repository.ToDoRepository;
import com.tecnotree.client.ApiClient;
import com.tecnotree.client.map.CommentClientDtoMapper;
import com.tecnotree.client.map.PostClientDtoMapper;
import com.tecnotree.client.map.ToDoClientDtoMapper;
import com.tecnotree.comment.reopsitory.CommentRepository;
import com.tecnotree.post.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * The Class DataInitializer read the json and save in database.
 */
@Component
@Slf4j
public class DataInitializer {

    private final PostClientDtoMapper postClientDtoMapper;
    private final CommentClientDtoMapper commentClientDtoMapper;
    private final ToDoClientDtoMapper toDoClientDtoMapper;
    private final ApiClient dataClient;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ToDoRepository toDoRepository;

    public DataInitializer(PostClientDtoMapper postClientDtoMapper, CommentClientDtoMapper commentClientDtoMapper,
                           ToDoClientDtoMapper toDoClientDtoMapper, ApiClient dataClient, PostRepository postRepository,
                           CommentRepository commentRepository, ToDoRepository toDoRepository) {
        this.postClientDtoMapper = postClientDtoMapper;
        this.commentClientDtoMapper = commentClientDtoMapper;
        this.toDoClientDtoMapper = toDoClientDtoMapper;
        this.dataClient = dataClient;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.toDoRepository = toDoRepository;
    }

    @PostConstruct
    public void init() {
        var postDtos = dataClient.getAllPosts();
        postRepository.saveAll(postClientDtoMapper.map(postDtos));
        log.info("The post was successfully registered");

        var commentDtos = dataClient.getAllComments();
        commentRepository.saveAll(commentClientDtoMapper.map(commentDtos));
        log.info("The comment was successfully registered");

        var toDoDtos = dataClient.getAllTodos();
        toDoRepository.saveAll(toDoClientDtoMapper.map(toDoDtos));
        log.info("The toDo was successfully registered");
    }

}
