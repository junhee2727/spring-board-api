package com.junhee.spring_board_api.post.service;

import com.junhee.spring_board_api.post.dto.PostCreateRequest;
import com.junhee.spring_board_api.post.dto.PostUpdateRequest;
import com.junhee.spring_board_api.post.entity.Post;
import com.junhee.spring_board_api.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostCreateRequest request) {
        Post post = new Post(
                request.getTitle(),
                request.getContent()
        );

        return postRepository.save(post);
    }

    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("post not found"));
    }

    @Transactional
    public Post updatePost(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("post not found"));
        post.update(request.getTitle(), request.getContent());
        return post;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
