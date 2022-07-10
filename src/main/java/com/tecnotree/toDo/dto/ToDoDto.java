package com.tecnotree.toDo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * The class ToDoDto output todos service .
 */
@Builder
@AllArgsConstructor
public class ToDoDto {

    private Long userId;
    private Long id;
    private String title;
    private boolean completed;

    public ToDoDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
