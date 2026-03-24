package com.junhee.spring_board_api.domain.post.dto;

import com.junhee.spring_board_api.domain.post.entity.Post;
import lombok.Getter;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;

    public PostResponse(Post post) {
        this.id = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
