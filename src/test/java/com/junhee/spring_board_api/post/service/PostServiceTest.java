package com.junhee.spring_board_api.post.service;

import com.junhee.spring_board_api.domain.post.dto.PostCreateRequest;
import com.junhee.spring_board_api.domain.post.entity.Post;
import com.junhee.spring_board_api.domain.post.service.PostService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    PostService postService;

    @Test
    void 게시글_생성(){
        PostCreateRequest post = new PostCreateRequest();
        post.setTitle("테스트 제목");
        post.setContent("테스트 내용");

        Post savedPost = postService.createPost(post);

        Assertions.assertThat(savedPost.getId()).isNotNull();
        Assertions.assertThat(savedPost.getTitle()).isEqualTo("테스트 제목");
    }
}
