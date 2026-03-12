package com.junhee.spring_board_api.post.controller;

import com.junhee.spring_board_api.post.entity.Post;
import com.junhee.spring_board_api.post.repository.PostRepository;
import com.junhee.spring_board_api.post.service.PostService;
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
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @GetMapping("/posts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable Long id){
        return postService.getPost(id);
    }
}
