package com.junhee.spring_board_api.domain.post.service;

import com.junhee.spring_board_api.domain.category.entity.Category;
import com.junhee.spring_board_api.domain.category.repository.CategoryRepository;
import com.junhee.spring_board_api.domain.member.entity.Member;
import com.junhee.spring_board_api.domain.member.repository.MemberRepository;
import com.junhee.spring_board_api.domain.member.service.MemberService;
import com.junhee.spring_board_api.domain.post.dto.PostCreateRequest;
import com.junhee.spring_board_api.domain.post.dto.PostUpdateRequest;
import com.junhee.spring_board_api.domain.post.entity.Post;
import com.junhee.spring_board_api.domain.post.repository.PostRepository;
import com.junhee.spring_board_api.global.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Post createPost(PostCreateRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("회원이 없습니다."));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("카테고리가 없습니다."));

        Post post = new Post(
                member,
                category,
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
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow();
        post.update(category,request.getTitle(), request.getContent());
        return post;
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);
        post.delete();
    }

    public Page<Post> getPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> searchPosts(String title, Pageable pageable){
        return postRepository.findByTitleContaining(title, pageable);
    }
}
