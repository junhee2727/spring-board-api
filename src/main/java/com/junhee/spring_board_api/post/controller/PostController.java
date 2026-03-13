package com.junhee.spring_board_api.post.controller;

import com.junhee.spring_board_api.post.dto.PostCreateRequest;
import com.junhee.spring_board_api.post.dto.PostResponse;
import com.junhee.spring_board_api.post.dto.PostUpdateRequest;
import com.junhee.spring_board_api.post.entity.Post;
import com.junhee.spring_board_api.post.repository.PostRepository;
import com.junhee.spring_board_api.post.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostRepository postRepository;
    private final PostService postService;

    public PostController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @PostMapping("/posts")
    public PostResponse createPost(@RequestBody PostCreateRequest request) {
        Post post = postService.createPost(request);

        return new PostResponse(post);
    }

    //getPosts 기본 사이즈를 10으로 제한
    @GetMapping("/posts")
    public Page<PostResponse> getPosts(@PageableDefault(size = 10) Pageable pageable) {
        return postService.getPosts(pageable)
                .map(PostResponse::new);
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable Long id){
        return postService.getPost(id);
    }

    @PutMapping("/posts/{id}")
    public PostResponse updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        Post post =  postService.updatePost(id, request);

        return new PostResponse(post);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id){
        postService.deletePost(id);
    }

}
