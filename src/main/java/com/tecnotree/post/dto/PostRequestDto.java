package com.tecnotree.post.dto;


/**
 * The class PostRequestDto input post api.
 */
public class PostRequestDto {

    private Long userId;
    private String title;
    private String body;

    public PostRequestDto(Long userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
