package com.junhee.spring_board_api.post.service;

import com.junhee.spring_board_api.domain.category.entity.Category;
import com.junhee.spring_board_api.domain.category.repository.CategoryRepository;
import com.junhee.spring_board_api.domain.member.entity.Member;
import com.junhee.spring_board_api.domain.member.repository.MemberRepository;
import com.junhee.spring_board_api.domain.post.dto.PostCreateRequest;
import com.junhee.spring_board_api.domain.post.dto.PostUpdateRequest;
import com.junhee.spring_board_api.domain.post.entity.Post;
import com.junhee.spring_board_api.domain.post.service.PostService;
import com.junhee.spring_board_api.global.exception.MemberNotFoundException;
import com.junhee.spring_board_api.global.exception.PostNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
public class PostServiceTest {

    @Autowired PostService postService;
    @Autowired MemberRepository memberRepository;
    @Autowired CategoryRepository categoryRepository;

    private Member member;
    private Category category;

    @BeforeEach
    void setUp() {
        member = memberRepository.save(new Member("test@test.com", "password123", "테스터"));
        category = categoryRepository.save(new Category("자유게시판", 1));
    }

    @Test
    void 게시글_생성_성공() {
        PostCreateRequest request = new PostCreateRequest(member.getMemberId(), category.getCategoryId(), "테스트 제목", "테스트 내용");

        Post savedPost = postService.createPost(request);

        assertThat(savedPost.getPostId()).isNotNull();
        assertThat(savedPost.getTitle()).isEqualTo("테스트 제목");
        assertThat(savedPost.getContent()).isEqualTo("테스트 내용");
        assertThat(savedPost.getStatus()).isEqualTo(Post.PostStatus.ACTIVE);
        assertThat(savedPost.getViewCount()).isEqualTo(0);
    }

    @Test
    void 게시글_생성_존재하지_않는_회원() {
        PostCreateRequest request = new PostCreateRequest(999L, category.getCategoryId(), "제목", "내용");

        assertThatThrownBy(() -> postService.createPost(request))
                .isInstanceOf(MemberNotFoundException.class);
    }

    @Test
    void 게시글_단건_조회_성공() {
        PostCreateRequest request = new PostCreateRequest(member.getMemberId(), category.getCategoryId(), "테스트 제목", "테스트 내용");
        Post saved = postService.createPost(request);

        Post found = postService.getPost(saved.getPostId());

        assertThat(found.getPostId()).isEqualTo(saved.getPostId());
        assertThat(found.getTitle()).isEqualTo("테스트 제목");
    }

    @Test
    void 게시글_단건_조회_없는_게시글() {
        assertThatThrownBy(() -> postService.getPost(999L))
                .isInstanceOf(PostNotFoundException.class);
    }

    @Test
    void 게시글_조회시_조회수_증가() {
        PostCreateRequest request = new PostCreateRequest(member.getMemberId(), category.getCategoryId(), "테스트 제목", "테스트 내용");
        Post saved = postService.createPost(request);

        postService.getPostAndIncreaseView(saved.getPostId());
        Post found = postService.getPost(saved.getPostId());

        assertThat(found.getViewCount()).isEqualTo(1);
    }

    @Test
    void 게시글_수정_성공() {
        PostCreateRequest createRequest = new PostCreateRequest(member.getMemberId(), category.getCategoryId(), "원래 제목", "원래 내용");
        Post saved = postService.createPost(createRequest);

        PostUpdateRequest updateRequest = new PostUpdateRequest();
        updateRequest.setCategoryId(category.getCategoryId());
        updateRequest.setTitle("수정된 제목");
        updateRequest.setContent("수정된 내용");

        Post updated = postService.updatePost(saved.getPostId(), updateRequest);

        assertThat(updated.getTitle()).isEqualTo("수정된 제목");
        assertThat(updated.getContent()).isEqualTo("수정된 내용");
    }

    @Test
    void 게시글_삭제_소프트딜리트() {
        PostCreateRequest request = new PostCreateRequest(member.getMemberId(), category.getCategoryId(), "테스트 제목", "테스트 내용");
        Post saved = postService.createPost(request);

        postService.deletePost(saved.getPostId());
        Post deleted = postService.getPost(saved.getPostId());

        assertThat(deleted.getStatus()).isEqualTo(Post.PostStatus.DELETED);
    }

    @Test
    void 게시글_목록_조회() {
        postService.createPost(new PostCreateRequest(member.getMemberId(), category.getCategoryId(), "제목1", "내용1"));
        postService.createPost(new PostCreateRequest(member.getMemberId(), category.getCategoryId(), "제목2", "내용2"));

        Page<Post> posts = postService.getPosts(PageRequest.of(0, 10));

        assertThat(posts.getContent()).hasSizeGreaterThanOrEqualTo(2);
    }

    @Test
    void 게시글_제목_검색() {
        postService.createPost(new PostCreateRequest(member.getMemberId(), category.getCategoryId(), "스프링 공부", "내용"));
        postService.createPost(new PostCreateRequest(member.getMemberId(), category.getCategoryId(), "JPA 공부", "내용"));

        Page<Post> result = postService.searchPosts("스프링", PageRequest.of(0, 10));

        assertThat(result.getContent()).hasSize(1);
        assertThat(result.getContent().get(0).getTitle()).isEqualTo("스프링 공부");
    }
}