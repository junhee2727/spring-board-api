package com.junhee.spring_board_api.post.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {

    private String title;
    private String content;
}
