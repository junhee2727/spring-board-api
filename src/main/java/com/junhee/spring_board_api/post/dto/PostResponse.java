package com.junhee.spring_board_api.post.dto;

import com.junhee.spring_board_api.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;

    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
