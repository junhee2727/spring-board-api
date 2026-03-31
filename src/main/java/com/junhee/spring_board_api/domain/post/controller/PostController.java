package com.junhee.spring_board_api.domain.post.controller;

import com.junhee.spring_board_api.domain.common.ApiResponse;
import com.junhee.spring_board_api.domain.post.dto.PostCreateRequest;
import com.junhee.spring_board_api.domain.post.dto.PostResponse;
import com.junhee.spring_board_api.domain.post.dto.PostUpdateRequest;
import com.junhee.spring_board_api.domain.post.entity.Post;
import com.junhee.spring_board_api.domain.post.repository.PostRepository;
import com.junhee.spring_board_api.domain.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostRepository postRepository, PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ApiResponse<PostResponse> createPost(@Valid @RequestBody PostCreateRequest request) {
        Post post = postService.createPost(request);

//        return new PostResponse(post);
        return new ApiResponse<>(200, new PostResponse(post));
    }

    //getPosts @PageableDefault(size=10) > 기본 사이즈를 10으로 제한
    @GetMapping("/posts")
    public ApiResponse<Page<PostResponse>> getPosts(@PageableDefault(size = 10) Pageable pageable) {
        return new ApiResponse<>(200, postService.getPosts(pageable)
                .map(PostResponse::new));
    }

    @GetMapping("/posts/{id}")
    public ApiResponse<PostResponse> getPost(@PathVariable Long id){
        return new ApiResponse<>(200,
                new PostResponse(postService.getPostAndIncreaseView(id)));
    }

    //@PageableDefault(size=10) > 기본 사이즈를 10으로 제한
    @GetMapping("/posts/search")
    public ApiResponse<Page<PostResponse>> searchPosts(@RequestParam String title,
                                          @PageableDefault(size = 10) Pageable pageable){
        return new ApiResponse<>(200, postService.searchPosts(title, pageable)
                .map(PostResponse::new));
    }

    @PutMapping("/posts/{id}")
    public ApiResponse<PostResponse> updatePost(@PathVariable Long id,@Valid @RequestBody PostUpdateRequest request) {
        Post post =  postService.updatePost(id, request);

        return new ApiResponse<>(200, new PostResponse(post));
    }

    @DeleteMapping("/posts/{id}")
    public ApiResponse<Void> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ApiResponse<>(200, null);
    }
}
