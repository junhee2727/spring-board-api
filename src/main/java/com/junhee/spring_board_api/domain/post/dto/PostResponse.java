package com.junhee.spring_board_api.domain.post.dto;

import com.junhee.spring_board_api.domain.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private int viewCount;
    private String categoryName;
    private String nickname;
    private LocalDateTime createdAt;

    public PostResponse(Post post) {
        this.id = post.getPostId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.viewCount = post.getViewCount();
        this.categoryName = post.getCategory().getName();
        this.nickname = post.getMember().getNickname();
        this.createdAt = post.getCreatedAt();
    }
}
