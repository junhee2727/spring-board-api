package com.junhee.spring_board_api.domain.post.service;

import com.junhee.spring_board_api.domain.post.dto.PostCreateRequest;
import com.junhee.spring_board_api.domain.post.dto.PostUpdateRequest;
import com.junhee.spring_board_api.domain.post.entity.Post;
import com.junhee.spring_board_api.domain.post.repository.PostRepository;
import com.junhee.spring_board_api.global.exception.PostNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(PostNotFoundException::new);
    }

    @Transactional
    public Post updatePost(Long id, PostUpdateRequest request) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        post.update(request.getTitle(), request.getContent());
        return post;
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> searchPosts(String title, Pageable pageable){
        return postRepository.findByTitleContaining(title, pageable);
    }
}
