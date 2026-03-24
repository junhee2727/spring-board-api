package com.junhee.spring_board_api.domain.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
public class PostCreateRequest {
    private Long memberId;
    private Long categoryId;

    @NotBlank(message = "제목은 필수입니다.")
    @Size(max = 255, message = "제목은 255자 이하입니다.")
    private String title;
    @NotBlank(message = "내용은 필수입니다.")
    private String content;
}
