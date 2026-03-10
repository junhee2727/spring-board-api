package com.junhee.spring_board_api.domain.post;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    private final PostRepository postRepository;

    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @PostMapping("/posts")
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    @GetMapping("/posts")
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/posts/{id}")
    public Post getPost(@PathVariable Long id){
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
}
